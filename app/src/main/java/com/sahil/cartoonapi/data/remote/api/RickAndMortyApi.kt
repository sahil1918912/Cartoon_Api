package com.sahil.cartoonapi.data.remote.api

import com.sahil.cartoonapi.data.remote.dto.CharacterDto
import com.sahil.cartoonapi.data.remote.dto.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(): CharacterResponseDto

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDto
}