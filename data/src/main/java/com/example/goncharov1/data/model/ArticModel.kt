package com.example.goncharov1.data.model

data class ArticModel(var data: List<ArticModelData>)

data class ArticModelData(
    var id: Int = 1,
    var title: String = "Empty",
    val department_title: String = "Empty",
    val main_reference_number: String = "Empty",
    val artist_display: String = "Empty"
)