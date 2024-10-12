package com.klyxdevs.kmptp2024.tpBase

import com.klyxdevs.kmptp2024.data.local.Character
import com.klyxdevs.kmptp2024.data.network.model.CharactersResponse

class KtorCharactersRepository(private val apiClient: MarvelCharactersClient) : CharactersRepository {

    override suspend fun getCharacters(timestamp: Long, md5: String): List<Character> {
        return apiClient.getAllCharacters(timestamp, md5).toModel()
    }

    private fun CharactersResponse.toModel(): List<Character> {
        return characters.list.map {
            Character(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnailUrl = it.thumbnail.toUrl()
            )
        }
    }
}