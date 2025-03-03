package com.sahil.cartoonapi.data.remote.mapper

import com.sahil.cartoonapi.data.remote.dto.CharacterDto
import com.sahil.cartoonapi.domain.model.Characters

fun CharacterDto.toDomainModel(): Characters {
    return Characters(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        imageUrl = image,
        origin = origin.name,
        location = location.name,
        episodes = episode
    )
}