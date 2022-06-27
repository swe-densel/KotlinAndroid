package ph.gcash.cadet.denselross.serrano.sample.model

import com.google.gson.annotations.SerializedName

class PokemonListResponse {
    @SerializedName("count")
    var count: Int = -1

    @SerializedName("next")
    var next: String? = ""

    @SerializedName("previous")
    var previous: String? = ""

    @SerializedName("results")
    var pokemonList: ArrayList<PokemonURL> = ArrayList()
}