package com.sahil.cartoonapi.domain.repository

import com.sahil.cartoonapi.domain.model.Characters

interface CharacterRepository {
    suspend fun getCharacters(): Result<List<Characters>>
    suspend fun getCharacterById(id: Int): Result<Characters>
}