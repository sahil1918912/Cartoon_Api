package com.sahil.cartoonapi.data.remote.dto

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: OriginDto,
    val location: LocationDto,
    val episode: List<String>
)

data class OriginDto(val name: String)
data class LocationDto(val name: String)