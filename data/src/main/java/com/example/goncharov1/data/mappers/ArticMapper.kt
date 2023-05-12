package com.example.goncharov1.data.mappers

import com.example.goncharov1.data.model.ArticModel
import com.example.goncharov1.domain.entity.ArticEntity
import javax.inject.Inject

class ArticMapperImpl @Inject constructor() : ArticMapper {
    override fun toDomain(userModel: ArticModel): List<ArticEntity> {
        return userModel.data.map {
            ArticEntity(
                id = it.id,
                remoteId = 0,
                title = it.title,
                artistDisplay = it.artist_display,
                imageId = it.image_id,
            )
        }
    }
}

interface ArticMapper {
    fun toDomain(userModel: ArticModel): List<ArticEntity>
}