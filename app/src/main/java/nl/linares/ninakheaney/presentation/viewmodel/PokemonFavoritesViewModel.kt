package nl.linares.ninakheaney.presentation.viewmodel

import nl.linares.ninakheaney.data.model.PokemonEntity
import nl.linares.ninakheaney.data.repository.PokemonRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.first

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val favoritesManager: PokemonFavoritesManager
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<PokemonEntity>>(emptyList())
    val favorites: StateFlow<List<PokemonEntity>> get() = _favorites

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            val favoriteIds = favoritesManager.favoriteIds.first()
            _favorites.value = favoriteIds.mapNotNull { id ->
                repository.fetchPokemonDetails(id)
            }
        }
    }

    fun toggleFavorite(pokemon: PokemonEntity) {
        viewModelScope.launch {
            favoritesManager.toggleFavorite(pokemon.id ?: return@launch)
            loadFavorites() // Refresh favorites list
        }
    }

    suspend fun isFavorite(pokemonId: Int): Boolean {
        return favoritesManager.favoriteIds.first().contains(pokemonId)
    }
}