package nl.linares.ninakheaney.data.model

data class PokemonEntity(
    val id: Int?,
    var name: String?,
    var types: List<PokemonType?>?,
    var stats: List<PokemonStatus?>?,
    var abilities: List<PokemonAbility?>?
) {
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}
