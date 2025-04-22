package nl.linares.ninakheaney.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavDestination(val route: String, val label: String, val icon: ImageVector) {
    data object Home : BottomNavDestination("home", "Home", Icons.Filled.Home)
    data object Favorites : BottomNavDestination("favorites", "Favorites", Icons.Filled.Favorite)
}