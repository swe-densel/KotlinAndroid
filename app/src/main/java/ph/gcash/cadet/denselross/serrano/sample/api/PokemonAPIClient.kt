package ph.gcash.cadet.denselross.serrano.sample.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ph.gcash.cadet.denselross.serrano.sample.model.PokemonInfoResponse
import ph.gcash.cadet.denselross.serrano.sample.model.PokemonListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object PokemonAPIClient {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    val getPokemonData:PokemonAPI
        get() {
            val gson = GsonBuilder().setLenient().create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(PokemonAPI::class.java)
        }
}

interface PokemonAPI {
    @GET("pokemon/")
    fun getList(
        @Query("offset") startIndex: Int,
        @Query("limit") limit: Int):
            Call<PokemonListResponse>

    @GET("pokemon/{pokemonId}/")
    fun getPokemonInfo(
        @Path("pokemonId") pokemonID: Int):
            Call<PokemonInfoResponse>
}