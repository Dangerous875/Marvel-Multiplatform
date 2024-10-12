package com.klyxdevs.kmptp2024.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterData(
    @SerialName("results")
    val list: List<CharacterResult>
)