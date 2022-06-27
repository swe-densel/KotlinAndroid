package ph.gcash.cadet.denselross.serrano.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ph.gcash.cadet.denselross.serrano.sample.adapter.NameAdapter
import ph.gcash.cadet.denselross.serrano.sample.adapter.PokemonInfoAdapter
import ph.gcash.cadet.denselross.serrano.sample.api.PokemonAPI
import ph.gcash.cadet.denselross.serrano.sample.api.PokemonAPIClient
import ph.gcash.cadet.denselross.serrano.sample.databinding.ActivityPokemonBinding
import ph.gcash.cadet.denselross.serrano.sample.model.PokemonListResponse
import ph.gcash.cadet.denselross.serrano.sample.model.PokemonURL
import ph.gcash.cadet.denselross.serrano.sample.ui.PokemonImageFragment
import ph.gcash.cadet.denselross.serrano.sample.ui.PokemonInfoFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonBinding
    private val LOG = "PokemonActivity"
    private var names = ArrayList<PokemonURL>()
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var recyclerViewAdapter: NameAdapter

    private val pokemonInfoFragment = PokemonInfoFragment()
    private val pokemonImageFragment = PokemonImageFragment()
    private lateinit var pokemonInfoAdapter: PokemonInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        initFragments()
        recyclerViewAdapter = NameAdapter(applicationContext, names)
//        binding.rvPokemons.adapter = recyclerViewAdapter
//        binding.rvPokemons.layoutManager = GridLayoutManager(applicationContext,3)
        binding.rvPokemons.adapter = recyclerViewAdapter
        binding.rvPokemons.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )

        var swipeCallback = SwipeCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        swipeCallback.nameAdapter = recyclerViewAdapter
        itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvPokemons)

//        val username = intent.getStringExtra("username")
//        val bundle = intent.extras
//        val bundleData = bundle?.getString("bundle_string")
//
//        Log.d(LOG, "Username: $username")
//        Log.i(LOG,"Bundle Data: $bundleData")
//        Log.w(LOG,"Bundle Data: $bundleData")
//        Log.e(LOG,"Bundle Data: $bundleData")
    }

    fun init(){
//        names.add("Bulbasaur")
//        names.add("Ivysaur")
//        names.add("Venusaur")
//        names.add("Charmander")
//        names.add("Charmeleon")
//        names.add("Charizard")
//        names.add("Squirtle")
//        names.add("Wartortle")
//        names.add("Blastoise")
//        names.add("Caterpie")
//        names.add("Metapod")
//        names.add("Butterfree")

        val call: Call<PokemonListResponse> =
            PokemonAPIClient.getPokemonData.getList(0, 100)

        call.enqueue(object : Callback<PokemonListResponse> {
            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                Log.d("API CALL", "Failed API Call")
            }

            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                var response: PokemonListResponse = response.body()!!
                recyclerViewAdapter.setNames(response.pokemonList)

//                var pokeList = response.pokemonList
//                for (pokemon in pokeList) {
//                    Log.d("API CALL", pokemon.name)
//                }

            }
        })
    }

    private fun initFragments(){
        pokemonInfoAdapter = PokemonInfoAdapter(supportFragmentManager)
        pokemonInfoAdapter.add(pokemonInfoFragment, "Pokemon Information")
        pokemonInfoAdapter.add(pokemonImageFragment, "Pokemon Image")
        binding.vpPokemon.adapter = pokemonInfoAdapter
    }
}