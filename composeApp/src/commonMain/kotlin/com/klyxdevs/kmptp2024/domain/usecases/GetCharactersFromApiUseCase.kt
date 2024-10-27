package com.klyxdevs.kmptp2024.domain.usecases

import com.klyxdevs.kmptp2024.data.core.PRIVATE_KEY
import com.klyxdevs.kmptp2024.data.core.PUBLIC_KEY
import com.klyxdevs.kmptp2024.data.local.Character
import com.klyxdevs.kmptp2024.data.local.toDomain
import com.klyxdevs.kmptp2024.domain.model.CharacterDomain
import com.klyxdevs.kmptp2024.domain.repository.Repository
import com.soywiz.krypto.MD5
import io.ktor.util.date.getTimeMillis
import io.ktor.utils.io.core.toByteArray

class GetCharactersFromApiUseCase(private val repository: Repository) {

    suspend operator fun invoke(): List<CharacterDomain> {
        val heroList = repository.getCharactersDataBase()
        if (heroList.isNotEmpty()) {
            return heroList.map { it.toDomain() }
        } else {
            try {
                val timestamp = getTimeMillis()
                val hash = md5(timestamp.toString() + PRIVATE_KEY + PUBLIC_KEY)
                val response = repository.getCharacters(timestamp = timestamp, md5 = hash)
                val orderList = sort(response)
                repository.deleteSuperHeroesSQLDelight()
                repository.insertSuperHeroesSQLDelight(orderList.map { it.toDomain() })
                return orderList.map { it.toDomain() }
            } catch (e: Exception) {
                return emptyList()
            }

        }
    }
}

private fun md5(string: String): String {
    val messageDigest = MD5.digest(string.toByteArray())
    return messageDigest.hex
}

private fun sort(characters: List<Character>): List<Character> {
    return characters.sortedWith(CharacterComparator())
}

/**
 * Los personajes se ordenan de la siguiente manera:
 * - Primero los que tienen descripción, y luego los que no.
 * - Los que tienen descripción a su vez se ordenan ascendentemente por su id.
 * - Los que NO tienen descripción se ordenan descendentemente por su id.
 */
private class CharacterComparator : Comparator<Character> {
    override fun compare(a: Character, b: Character): Int {
        if (a.description.isEmpty() && b.description.isEmpty()) {
            return b.id.compareTo(a.id)
        }
        if (a.description.isNotEmpty() && b.description.isNotEmpty()) {
            return a.id.compareTo(b.id)
        }
        if (a.description.isNotEmpty() && b.description.isEmpty()) {
            return -1
        }
        return 1
    }

}