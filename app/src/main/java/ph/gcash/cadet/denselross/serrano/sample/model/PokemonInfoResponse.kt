package ph.gcash.cadet.denselross.serrano.sample.model

import com.google.gson.annotations.SerializedName

class PokemonInfoResponse {
    @SerializedName("name")
    var name = ""

    @SerializedName("height")
    var height = -1

    @SerializedName("id")
    var id = -1

    @SerializedName("abilities")
    var abilities:ArrayList<PokemonAbilityInfo> = ArrayList()

    @SerializedName("sprites")
    var sprites:PokemonSprite = PokemonSprite()
}

class PokemonAbilityInfo {
    @SerializedName("ability")
    var ability: PokemonAbility = PokemonAbility()

    @SerializedName("is_hidden")
    var is_hidden = false

    @SerializedName("slot")
    var slot = -1
}

class PokemonAbility {
    @SerializedName("name")
    var name = ""

    @SerializedName("url")
    var url = ""
}

class PokemonSprite {
    @SerializedName("front_default")
    var frontDefault = ""
}