package nl.linares.ninakheaney.presentation.viewmodel

import nl.linares.ninakheaney.data.model.PokemonEntity
import nl.linares.ninakheaney.data.repository.PokemonRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemon = MutableStateFlow<PokemonEntity?>(null)
    val pokemon: StateFlow<PokemonEntity?> get() = _pokemon

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun fetchPokemonDetails(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val pokemonDetails = repository.fetchPokemonDetails(id)
                _pokemon.value = pokemonDetails
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load Pokémon details. Please try again."
            } finally {
                _isLoading.value = false
            }
        }
    }
}