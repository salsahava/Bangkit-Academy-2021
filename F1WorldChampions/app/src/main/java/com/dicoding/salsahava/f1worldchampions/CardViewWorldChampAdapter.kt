package com.dicoding.salsahava.f1worldchampions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.w3c.dom.Text

class CardViewWorldChampAdapter(private val listWorldChamp: ArrayList<WorldChamp>) :
    RecyclerView.Adapter<CardViewWorldChampAdapter.CardViewViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_worldchamp, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listWorldChamp.size
    }

    override fun onBindViewHolder(
        holder: CardViewWorldChampAdapter.CardViewViewHolder,
        position: Int
    ) {
        val worldChamp = listWorldChamp[position]

        Glide.with(holder.itemView.context)
                .load(worldChamp.photo)
                .apply(RequestOptions().override(350, 550))
                .into(holder.imgPhoto)

        holder.tvName.text = worldChamp.name
        holder.tvDetail.text = worldChamp.detail

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listWorldChamp[holder.adapterPosition])
        }
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: WorldChamp)
    }
}