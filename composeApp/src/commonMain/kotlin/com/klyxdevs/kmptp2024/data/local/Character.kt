package com.klyxdevs.kmptp2024.data.local

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String
)