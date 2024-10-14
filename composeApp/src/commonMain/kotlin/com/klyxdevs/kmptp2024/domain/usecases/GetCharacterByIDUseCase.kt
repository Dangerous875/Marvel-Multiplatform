package com.klyxdevs.kmptp2024.domain.usecases

import com.klyxdevs.kmptp2024.data.local.toDomain
import com.klyxdevs.kmptp2024.domain.model.CharacterDomain
import com.klyxdevs.kmptp2024.domain.repository.Repository

class GetCharacterByIDUseCase(private val repository: Repository) {
    operator fun invoke(id:Long):CharacterDomain{
        val character = repository.getCharactersByIdDataBase(id)
        return character.toDomain()
    }
}