package com.klyxdevs.kmptp2024.tpBase

import com.klyxdevs.kmptp2024.data.local.Character

interface CharactersRepository {
    suspend fun getCharacters(timestamp: Long, md5: String): List<Character>
}