package nl.linares.ninakheaney.data.repository

import nl.linares.ninakheaney.data.api.PokemonApi
import nl.linares.ninakheaney.data.model.PokemonAbility
import nl.linares.ninakheaney.data.model.PokemonEntity
import nl.linares.ninakheaney.data.model.PokemonStatus
import nl.linares.ninakheaney.data.model.PokemonType
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
): PokemonRepository {
    override suspend fun fetchPokemons(): List<PokemonEntity> {
        return pokemonApi.getPokemons()
    }

    override suspend fun fetchPokemonDetails(id: Int): PokemonEntity {
        return pokemonApi.getPokemonDetails(id)
    }

    override suspend fun fetchPokemonTypes(id: Int): PokemonType {
        return pokemonApi.getPokemonTypes(id)
    }

    override suspend fun fetchPokemonStats(id: Int): PokemonStatus {
        return pokemonApi.getPokemonStats(id)
    }

    override suspend fun fetchPokemonAbilities(id: Int): PokemonAbility {
        return pokemonApi.getPokemonAbilities(id)
    }
}