package ph.gcash.cadet.denselross.serrano.sample.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ph.gcash.cadet.denselross.serrano.sample.GCashApplication
import ph.gcash.cadet.denselross.serrano.sample.api.PokemonAPIClient
import ph.gcash.cadet.denselross.serrano.sample.databinding.ItemNameBinding
import ph.gcash.cadet.denselross.serrano.sample.model.PokemonInfoResponse
import ph.gcash.cadet.denselross.serrano.sample.model.PokemonURL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NameAdapter(private val context: Context,
                  private var names: ArrayList<PokemonURL>)
    : RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : NameViewHolder {
        val itemBinding = ItemNameBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return NameViewHolder(itemBinding)
    }

    override fun getItemCount() = names.size
    override fun onBindViewHolder(holder: NameViewHolder, position: Int){
        holder.bindItems(names[position])
    }

    inner class NameViewHolder(private val itemBinding: ItemNameBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItems(data: PokemonURL){
            if (adapterPosition % 2 == 0){
                itemBinding.root.setCardBackgroundColor(Color.LTGRAY)
            }
            itemBinding.tvName.text = data.name

            itemBinding.root.setOnClickListener{
                Snackbar.make(itemBinding.root, data.name, Snackbar.LENGTH_SHORT).show()
            }

            itemBinding.btnCall.setOnClickListener {
                Snackbar.make(itemBinding.root, "GO ${data.name}!", Snackbar.LENGTH_SHORT).show()

                getData(data.url.getPokemonID())
            }
        }
    }

    private fun getData(id: Int) {
        val call: Call<PokemonInfoResponse> =
            PokemonAPIClient.getPokemonData.getPokemonInfo(id)

        call.enqueue(object : Callback<PokemonInfoResponse> {
            override fun onResponse(
                call: Call<PokemonInfoResponse>,
                response: Response<PokemonInfoResponse>
            ) {
                val pokemonInfoResponse: PokemonInfoResponse = response.body()!!
                Log.d("API Pokemon CALL", "${pokemonInfoResponse.sprites.frontDefault}")
                Log.d("API Pokemon CALL", pokemonInfoResponse.name)

                Intent().also {
                    it.action = GCashApplication.BROADCASTLOADIMAGE
                    it.putExtra("data", pokemonInfoResponse.sprites.frontDefault)
                    context.sendBroadcast(it)
                }

                Intent().also {
                    it.action = GCashApplication.BROADCASTLOADDATA
                    it.putExtra("name", pokemonInfoResponse.name)
                    it.putExtra("id", pokemonInfoResponse.id)
                    it.putExtra("height", pokemonInfoResponse.height)
                    context.sendBroadcast(it)
                }

            }

            override fun onFailure(call: Call<PokemonInfoResponse>, t: Throwable) {
                Log.d("API Pokemon CALL", "API FAILED")
            }

        })
    }

    fun removeName(position: Int) {
        names.removeAt(position)
        notifyItemChanged(position)
        notifyDataSetChanged()
    }

    fun setNames(newNames: ArrayList<PokemonURL>) {
        names.clear()
        names.addAll(newNames)
        notifyDataSetChanged()
    }

    fun String.getPokemonID(): Int = this.substring(34, this.length-1).toInt()

}