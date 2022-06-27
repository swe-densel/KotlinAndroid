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
import ph.gcash.cadet.denselross.serrano.sample.databinding.FragmentPokemonInfoBinding

class PokemonInfoFragment : Fragment() {
    private lateinit var binding: FragmentPokemonInfoBinding

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            val id: Int = intent!!.getIntExtra("id", -1)
            val name: String? = intent.getStringExtra("name")
            val height: Int = intent.getIntExtra("height", -1)

            binding.textPokemonName.text = name
            binding.textPokemonId.text = id.toString()
            binding.textPokemonHeight.text = height.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonInfoBinding.inflate(inflater, container, false)
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
        intentFilter.addAction(GCashApplication.BROADCASTLOADDATA)
        requireActivity().registerReceiver(receiver, intentFilter)
    }

}