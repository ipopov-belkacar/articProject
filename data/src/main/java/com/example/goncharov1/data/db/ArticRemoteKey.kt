package com.example.goncharov1.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prev: Int?,
    val next: Int?
)