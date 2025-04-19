package com.example.jewelryapp.model

data class Product(
    val name: String,
    val imageRes: Int,
    val price: Double,
    val category: String,
    val reviews: List<String> = listOf(),
    val averageRating: Float = 4.5f
)
