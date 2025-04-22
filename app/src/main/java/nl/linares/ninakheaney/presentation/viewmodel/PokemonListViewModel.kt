package nl.linares.ninakheaney.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.linares.ninakheaney.data.model.PokemonEntity
import nl.linares.ninakheaney.data.repository.PokemonRepository
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var pokemons by mutableStateOf<List<PokemonEntity>>(emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchPokemons()
    }

    fun fetchPokemons() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                pokemons = repository.fetchPokemons()
            } catch (e: Exception) {
                errorMessage = "Failed to load data. Please try again."
            } finally {
                isLoading = false
            }
        }
    }
}