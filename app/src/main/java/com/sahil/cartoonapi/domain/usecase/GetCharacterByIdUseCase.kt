package com.sahil.cartoonapi.domain.usecase

import com.sahil.cartoonapi.domain.model.Characters
import com.sahil.cartoonapi.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Result<Characters> {
        return repository.getCharacterById(id)
    }
}