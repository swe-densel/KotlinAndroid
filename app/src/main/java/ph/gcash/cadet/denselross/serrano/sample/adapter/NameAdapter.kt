package ph.gcash.cadet.denselross.serrano.sample.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ph.gcash.cadet.denselross.serrano.sample.databinding.ItemNameBinding

class NameAdapter(private val context: Context,
                  private var names: ArrayList<String>)
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

        fun bindItems(data: String){
            if (adapterPosition % 2 == 0){
                itemBinding.root.setCardBackgroundColor(Color.LTGRAY)
            }
            itemBinding.tvName.text = data

            itemBinding.root.setOnClickListener{
                Snackbar.make(itemBinding.root, data, Snackbar.LENGTH_SHORT).show()
            }

            itemBinding.btnCall.setOnClickListener {
                Snackbar.make(itemBinding.root, "GO $data!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }



}