package com.klyxdevs.kmptp2024.data.local

import com.klyxdevs.kmptp2024.data.network.model.Thumbnail
import com.klyxdevs.kmptp2024.domain.model.CharacterDomain
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: Thumbnail
)

fun Character.toDomain() = CharacterDomain(
    id = this.id,
    name = this.name,
    description = this.description.ifEmpty {
        "The description does not exist, very bad API :("
    },
    imageURL = "${thumbnailUrl.path}.${thumbnailUrl.extension}"
)