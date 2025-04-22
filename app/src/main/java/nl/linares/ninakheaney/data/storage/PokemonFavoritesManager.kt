package nl.linares.ninakheaney.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("favorites")

@Singleton
class PokemonFavoritesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        private val FAVORITES_KEY = preferencesKey<Set<Int>>("favorites")
    }

    val favoriteIds: Flow<Set<Int>> = dataStore.data
        .map { preferences -> preferences[FAVORITES_KEY] ?: emptySet() }

    suspend fun toggleFavorite(id: Int) {
        dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITES_KEY] ?: emptySet()
            preferences[FAVORITES_KEY] = if (id in currentFavorites) {
                currentFavorites - id
            } else {
                currentFavorites + id
            }
        }
    }
}