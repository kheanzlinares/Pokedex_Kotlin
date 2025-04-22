package nl.linares.ninakheaney.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.linares.ninakheaney.presentation.viewmodel.FavoritesViewModel
import nl.linares.ninakheaney.presentation.viewmodel.PokemonListViewModel

@Composable
fun PokemonFavorites(
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favorites = viewModel.favorites.collectAsState().value

    if (favorites.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No favorites added yet.")
        }
    } else {
        LazyColumn {
            items(favorites) { pokemon ->
                PokemonCard(
                    pokemonName = pokemon.name ?: "",
                    imageUrl = pokemon.imageUrl,
                    onClick = { navController.navigate("pokemon_detail/${pokemon.id}") }
                )
            }
        }
    }
}