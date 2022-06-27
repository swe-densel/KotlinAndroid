package ph.gcash.cadet.denselross.serrano.sample.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ph.gcash.cadet.denselross.serrano.sample.GCashApplication
import ph.gcash.cadet.denselross.serrano.sample.R
import ph.gcash.cadet.denselross.serrano.sample.databinding.FragmentPokemonImageBinding

class PokemonImageFragment : Fragment() {
    private lateinit var binding: FragmentPokemonImageBinding

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            //filter action
            var imageUrl: String? = intent!!.getStringExtra("data")

            imageUrl?.let {
                Picasso
                    .get()
                    .load(it)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .placeholder(R.drawable.pokeball)
                    .error(R.drawable.questionmark)
                    .into(binding!!.imagePokemon)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        setupReceiver()
    }

    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(receiver)
    }

    private fun setupReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(GCashApplication.BROADCASTLOADIMAGE)
        requireActivity().registerReceiver(receiver, intentFilter)
    }

}