package nl.linares.ninakheaney.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import nl.linares.ninakheaney.presentation.screens.PokemonDetail
import nl.linares.ninakheaney.presentation.screens.PokemonFavorites
import nl.linares.ninakheaney.presentation.screens.PokemonList

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavDestination.Home.route) {
        composable(BottomNavDestination.Home.route) {
            PokemonList(navController = navController)
        }
        composable(BottomNavDestination.Favorites.route) {
            PokemonFavorites(navController = navController)
        }
        composable(
            route = "pokemon_detail/{pokemonId}",
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemonId") ?: 0
            PokemonDetail(pokemonId = pokemonId)
        }
    }
}