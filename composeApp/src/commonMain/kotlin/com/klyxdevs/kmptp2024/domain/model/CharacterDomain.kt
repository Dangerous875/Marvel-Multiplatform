package com.klyxdevs.kmptp2024.domain.model

import com.klyxdevs.kmptp2024.data.local.Character
import com.klyxdevs.kmptp2024.data.network.model.Thumbnail

data class CharacterDomain(
    val id: Long,
    val name: String,
    val description: String,
    val imageURL: String
)

fun CharacterDomain.toDataBase(): Character {
    val (path, extension) = imageURL.substringBeforeLast(".") to imageURL.substringAfterLast(".")

    return Character(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnailUrl = Thumbnail(path, extension)
    )
}

