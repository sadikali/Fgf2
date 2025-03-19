package com.example.fgfsample2.model

data class Photos(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
    var like: Boolean = false
)
