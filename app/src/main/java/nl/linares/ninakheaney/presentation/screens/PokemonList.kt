package nl.linares.ninakheaney.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import nl.linares.ninakheaney.presentation.viewmodel.PokemonListViewModel

@Composable
fun PokemonList(viewModel: PokemonListViewModel = hiltViewModel()) {
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    val pokemons = viewModel.pokemons

    if (isLoading) {
        // Center the CircularProgressIndicator in the screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
    } else if (errorMessage != null) {
        // Center the error message and retry button in the screen
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = errorMessage)
            Button(onClick = { viewModel.fetchPokemons() }) {
                Text("Retry")
            }
        }
    } else {
        // Display the list of Pokémon
        LazyColumn {
            items(pokemons) { pokemon ->
                PokemonCard(pokemonName = pokemon.name ?: "", imageUrl = pokemon.imageUrl)
            }
        }
    }
}