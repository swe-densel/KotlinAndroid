package ph.gcash.cadet.denselross.serrano.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ph.gcash.cadet.denselross.serrano.sample.adapter.NameAdapter
import ph.gcash.cadet.denselross.serrano.sample.databinding.ActivityPokemonBinding

class PokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonBinding
    private val LOG = "PokemonActivity"
    private var names = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        var recyclerViewAdapter = NameAdapter(applicationContext, names)
//        binding.rvPokemons.adapter = recyclerViewAdapter
//        binding.rvPokemons.layoutManager = GridLayoutManager(applicationContext,3)

        binding.rvPokemons.adapter = recyclerViewAdapter
        binding.rvPokemons.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )

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
        names.add("Bulbasaur")
        names.add("Ivysaur")
        names.add("Venusaur")
        names.add("Charmander")
        names.add("Charmeleon")
        names.add("Charizard")
        names.add("Squirtle")
        names.add("Wartortle")
        names.add("Blastoise")
        names.add("Caterpie")
        names.add("Metapod")
        names.add("Butterfree")
    }
}