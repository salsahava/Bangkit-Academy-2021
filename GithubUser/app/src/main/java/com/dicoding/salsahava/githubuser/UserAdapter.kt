package com.dicoding.salsahava.githubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.salsahava.githubuser.databinding.ItemUserBinding

class UserAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.CardViewViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.CardViewViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.CardViewViewHolder, position: Int) {
        val user = listUser[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions().override(80, 80))
            .into(holder.imgAvatar)

        holder.tvName.text = user.name
        holder.tvUsername.text = user.username
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemUserBinding.bind(itemView)

        var imgAvatar = binding.imgItemPhoto
        var tvName = binding.tvItemName
        var tvUsername = binding.tvItemUsername
    }
}