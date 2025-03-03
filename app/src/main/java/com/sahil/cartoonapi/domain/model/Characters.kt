package com.sahil.cartoonapi.domain.model

data class Characters(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val imageUrl: String,
    val origin: String,
    val location: String,
    val episodes: List<String>
)