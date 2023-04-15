package com.assesment.grayhat.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.assesment.grayhat.R
import com.assesment.grayhat.data.Model.Items

class ItemAdapter(
    private val list: List<Items>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_watch, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.tName.text = list[position].name
        holder.tCat.text = list[position].category
        holder.tPrice.text = list[position].price.toString()
        val url = list[position].imgURL
        url?.let {
            val imgUri = url.toUri().buildUpon()?.scheme("https")?.build()
            holder.images.load(imgUri)
        }

        holder.card.setOnClickListener {
            listener.onItemClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var images: ImageView = itemView.findViewById(R.id.iv_Watch)
        var tName: TextView = itemView.findViewById(R.id.tv_Name)
        var tCat: TextView = itemView.findViewById(R.id.tv_Cat)
        var tPrice: TextView = itemView.findViewById(R.id.tv_Price)
        var card: CardView = itemView.findViewById(R.id.card)
    }
}