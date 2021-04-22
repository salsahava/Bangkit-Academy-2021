package com.dicoding.salsahava.githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.salsahava.githubuser.Follower
import com.dicoding.salsahava.githubuser.Following
import com.dicoding.salsahava.githubuser.UserItem
import com.dicoding.salsahava.githubuser.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private val userList = ArrayList<UserItem>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setUserList(userItems: ArrayList<UserItem>) {
        userList.clear()
        userList.addAll(userItems)
        notifyDataSetChanged()
    }

    fun setFollowerList(followers: ArrayList<Follower>) {
        userList.clear()
        for (item in followers) {
            val userItem = UserItem(username = item.username, avatarUrl = item.avatarUrl)
            userList.add(userItem)
        }
        notifyDataSetChanged()
    }

    fun setFollowingList(following: ArrayList<Following>) {
        userList.clear()
        for (item in following) {
            val userItem = UserItem(username = item.username, avatarUrl = item.avatarUrl)
            userList.add(userItem)
        }
        notifyDataSetChanged()
    }

    fun addItem(user: UserItem) {
        userList.add(user)
        notifyItemInserted(userList.size - 1)
    }

    fun updateItem(position: Int, user: UserItem) {
        userList[position] = user
        notifyItemChanged(position, user)
    }

    fun removeItem(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, userList.size)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {

        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = userList[position]

        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .apply(RequestOptions().override(64, 64))
            .into(holder.imgItemPhoto)

        holder.tvItemUsername.text = user.username

        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(user, position)
        }
    }

    override fun getItemCount(): Int = userList.size

    inner class ListViewHolder(binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        var imgItemPhoto = binding.imgItemPhoto
        var tvItemUsername = binding.tvItemUsername
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserItem, position: Int)
    }
}