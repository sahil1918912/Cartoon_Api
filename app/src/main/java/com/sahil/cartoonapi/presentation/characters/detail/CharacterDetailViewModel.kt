package com.sahil.cartoonapi.presentation.characters.detail

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahil.cartoonapi.domain.model.Characters
import com.sahil.cartoonapi.domain.usecase.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CharacterDetailState())
    val state: State<CharacterDetailState> = _state

    init {
        savedStateHandle.get<Int>("characterId")?.let { characterId ->
            getCharacter(characterId)
        }
    }

    private fun getCharacter(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            getCharacterByIdUseCase(id).fold(
                onSuccess = { character ->
                    _state.value = _state.value.copy(
                        characters = character,
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = error.message ?: "Unknown error occurred"
                    )
                }
            )
        }
    }
}

data class CharacterDetailState(
    val characters: Characters? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
