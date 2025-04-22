package nl.linares.ninakheaney.data.repository

import nl.linares.ninakheaney.data.model.PokemonAbility
import nl.linares.ninakheaney.data.model.PokemonEntity
import nl.linares.ninakheaney.data.model.PokemonStatus
import nl.linares.ninakheaney.data.model.PokemonType

interface PokemonRepository {
    //fetching all cardSets//
    suspend fun fetchPokemons():List<PokemonEntity>
    suspend fun fetchPokemonDetails(id: Int): PokemonEntity
    suspend fun fetchPokemonTypes(id: Int): PokemonType
    suspend fun fetchPokemonStats(id: Int): PokemonStatus
    suspend fun fetchPokemonAbilities(id: Int): PokemonAbility
}