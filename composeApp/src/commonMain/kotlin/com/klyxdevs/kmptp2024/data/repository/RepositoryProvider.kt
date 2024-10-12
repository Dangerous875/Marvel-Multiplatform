package com.klyxdevs.kmptp2024.data.repository

import com.klyxdevs.kmptp2024.data.local.Character
import com.klyxdevs.kmptp2024.data.network.model.CharactersResponse
import com.klyxdevs.kmptp2024.data.network.service.APIService
import com.klyxdevs.kmptp2024.domain.repository.Repository

class RepositoryProvider(private val api : APIService):Repository {
    override suspend fun getCharacters(timestamp: Long, md5: String): List<Character> {
        return api.getAllCharacters(timestamp, md5).toModel()
    }

    private fun CharactersResponse.toModel(): List<Character> {
        return characters.list.map {
            Character(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnailUrl = it.thumbnail
            )
        }
    }


}