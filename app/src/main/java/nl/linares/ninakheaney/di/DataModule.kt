package nl.linares.ninakheaney.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.linares.ninakheaney.data.repository.PokemonRepository
import nl.linares.ninakheaney.data.repository.PokemonRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindsPokemonRepository(
        questionCardRepositoryImpl: PokemonRepositoryImpl,
    ): PokemonRepository
}