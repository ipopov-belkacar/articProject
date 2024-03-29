package com.example.goncharov1.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticEntity(
    @PrimaryKey(autoGenerate = true)
    var remoteId: Int,
    var id: Int,
    var title: String,
    val artistDisplay: String,
    val imageId: String?
)