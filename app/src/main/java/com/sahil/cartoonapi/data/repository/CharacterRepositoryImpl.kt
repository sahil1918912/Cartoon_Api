package com.sahil.cartoonapi.data.repository

import com.sahil.cartoonapi.data.remote.api.RickAndMortyApi
import com.sahil.cartoonapi.data.remote.mapper.toDomainModel
import com.sahil.cartoonapi.domain.model.Characters
import com.sahil.cartoonapi.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : CharacterRepository {

    override suspend fun getCharacters(): Result<List<Characters>> {
        return try {
            val response = api.getCharacters()
            Result.success(response.results.map { it.toDomainModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCharacterById(id: Int): Result<Characters> {
        return try {
            val response = api.getCharacterById(id)
            Result.success(response.toDomainModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}