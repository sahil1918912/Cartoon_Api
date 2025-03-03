package com.sahil.cartoonapi.presentation.characters.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahil.cartoonapi.domain.model.Characters
import com.sahil.cartoonapi.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CharactersListState())
    val state: State<CharactersListState> = _state

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            getCharactersUseCase().fold(
                onSuccess = { characters ->
                    _state.value = _state.value.copy(
                        characters = characters,
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

data class CharactersListState(
    val characters: List<Characters> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)