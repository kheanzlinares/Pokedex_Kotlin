package nl.linares.ninakheaney.data.api

import nl.linares.ninakheaney.data.model.PokemonAbility
import nl.linares.ninakheaney.data.model.PokemonEntity
import nl.linares.ninakheaney.data.model.PokemonStatus
import nl.linares.ninakheaney.data.model.PokemonType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    // Pokémon list
    @GET("pokemon")
    suspend fun getPokemons(): List<PokemonEntity>

    // Pokémon details
    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") id: Int
    ): PokemonEntity

    // Pokémon types
    @GET("pokemon/{id}")
    suspend fun getPokemonTypes(
        @Path("id") id: Int
    ): PokemonType

    // Pokémon stats
    @GET("pokemon/{id}")
    suspend fun getPokemonStats(
        @Path("id") id: Int
    ): PokemonStatus

    // Pokémon abilities
    @GET("pokemon/{id}")
    suspend fun getPokemonAbilities(
        @Path("id") id: Int
    ): PokemonAbility
}