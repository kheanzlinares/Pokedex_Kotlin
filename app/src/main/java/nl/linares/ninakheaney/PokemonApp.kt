package nl.linares.ninakheaney

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import nl.linares.ninakheaney.util.BottomNavGraph
import nl.linares.ninakheaney.util.BottomNavigationBar

@Composable
fun PokemonApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            BottomNavGraph(navController)
        }
    }
}