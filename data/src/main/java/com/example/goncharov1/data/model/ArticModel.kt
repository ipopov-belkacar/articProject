package com.example.goncharov1.data.model

data class ArticModel(var data: List<ArticModelData>)

data class ArticModelData(
    var id: Int,
    var title: String,
    val department_title: String,
    val main_reference_number: String,
    val artist_display: String,
    val image_id: String?
)