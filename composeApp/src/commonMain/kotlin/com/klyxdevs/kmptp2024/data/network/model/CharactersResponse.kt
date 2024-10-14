package com.klyxdevs.kmptp2024.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    @SerialName("data") val characters: CharacterData
)