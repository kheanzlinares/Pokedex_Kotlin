package nl.linares.ninakheaney.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import nl.linares.ninakheaney.presentation.viewmodel.PokemonDetailViewModel
import coil.compose.AsyncImage
import nl.linares.ninakheaney.presentation.viewmodel.FavoritesViewModel

@Composable
fun PokemonDetailScreen(
    pokemonId: Int,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val pokemon = viewModel.pokemon.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val errorMessage = viewModel.errorMessage.collectAsState().value
    val isFavorite = remember { mutableStateOf(false) }

    LaunchedEffect(pokemonId) {
        isFavorite.value = favoritesViewModel.isFavorite(pokemonId)
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
    } else if (errorMessage != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = errorMessage)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { viewModel.fetchPokemonDetails(pokemonId) }) {
                Text("Retry")
            }
        }
    } else if (pokemon != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Name: ${pokemon.name}")
            Text(text = "ID: ${pokemon.id}")
            Text(text = "Type(s): ${pokemon.types?.joinToString { it?.name ?: "Unknown" }}")

            IconButton(onClick = {
                favoritesViewModel.toggleFavorite(pokemon)
                isFavorite.value = !isFavorite.value
            }) {
                Icon(
                    imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isFavorite.value) "Unfavorite" else "Favorite",
                    tint = if (isFavorite.value) Color.Red else Color.Gray
                )
            }
        }
    }
}