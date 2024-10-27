package com.klyxdevs.kmptp2024.domain.usecases

import com.klyxdevs.kmptp2024.domain.model.CharacterDomain
import com.klyxdevs.kmptp2024.domain.model.toDataBase
import com.klyxdevs.kmptp2024.domain.repository.Repository

class GetCharactersFromSQLDelight(private val repository: Repository) {

    suspend operator fun invoke(): List<CharacterDomain> {
        try {
            val heroesInDB = repository.getSuperHeroesSQLDelight()
            repository.setCharactersDataBase(heroesInDB.map { it.toDataBase() })
            return heroesInDB
        }catch (e: Exception){
            return emptyList()
        }

    }
}