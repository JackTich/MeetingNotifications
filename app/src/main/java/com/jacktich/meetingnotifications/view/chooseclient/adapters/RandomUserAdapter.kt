package com.jacktich.meetingnotifications.view.chooseclient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.jacktich.meetingnotifications.R
import com.jacktich.meetingnotifications.data.api.responses.DataUser
import com.squareup.picasso.Picasso

class RandomUserAdapter(var usersList: List<DataUser>, val callback: OnRandomUserClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_STANDARD = 0
        const val TYPE_SHIMMER = 1
    }

    override fun getItemViewType(position: Int): Int =
        if (usersList.isNotEmpty()) TYPE_STANDARD else TYPE_SHIMMER

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == TYPE_STANDARD) {
            StandardViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_random_user, parent, false)
            )
        } else {
            ShimmerViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.shimmer_item_list_random_user, parent, false)
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = if (getItemViewType(position) == TYPE_STANDARD){
        (holder as StandardViewHolder).bind(usersList[position])
    }else{
        (holder as ShimmerViewHolder).bind()
    }

    override fun getItemCount(): Int = if (usersList.isNotEmpty()) usersList.size else 15

    inner class ShimmerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val shimmerItemRandomUser = itemView.findViewById<ShimmerFrameLayout>(R.id.shimmerItemRandomUser)

        fun bind(){
            shimmerItemRandomUser.startShimmer()
        }
    }

    inner class StandardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val imgRandomUser = itemView.findViewById<ImageView>(R.id.imgRandomUser)
        private val tvNameRandomUser = itemView.findViewById<TextView>(R.id.tvNameRandomUser)
        private val tvEmailRandomUser = itemView.findViewById<TextView>(R.id.tvEmailRandomUser)

        fun bind(user: DataUser){
            itemView.setOnClickListener(this)
            Picasso.get()
                .load(user.picture.medium)
                .error(ContextCompat.getDrawable(itemView.context, R.drawable.ic_error)!!)
                .fit()
                .centerCrop()
                .into(imgRandomUser)
            tvNameRandomUser.text = "${user.name.title} ${user.name.first} ${user.name.last}"
            tvEmailRandomUser.text = user.email
        }

        override fun onClick(v: View?) {
            callback.onClick(usersList[absoluteAdapterPosition])
        }

    }

    interface OnRandomUserClick {
        fun onClick(user: DataUser)
    }

}