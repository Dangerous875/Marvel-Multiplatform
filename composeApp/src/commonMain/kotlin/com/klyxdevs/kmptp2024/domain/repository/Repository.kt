package com.klyxdevs.kmptp2024.domain.repository

import com.klyxdevs.kmptp2024.data.local.Character
import com.klyxdevs.kmptp2024.domain.model.CharacterDomain

interface Repository {
    suspend fun getCharacters(timestamp: Long, md5: String): List<Character>

    fun setCharactersDataBase(list: List<Character>)

    fun getCharactersDataBase():List<Character>

    fun getCharactersByIdDataBase(id:Long):Character

    suspend fun insertSuperHeroesSQLDelight(list: List<CharacterDomain>)

    suspend fun getSuperHeroesSQLDelight():List<CharacterDomain>

    suspend fun deleteSuperHeroesSQLDelight()
}