package com.jacktich.meetingnotifications.view.meetingslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jacktich.meetingnotifications.R
import com.jacktich.meetingnotifications.data.database.MeetingEntity
import com.squareup.picasso.Picasso

class MeetingsListAdapter(var meetingList: List<MeetingEntity>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MeetingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_meetings, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as MeetingViewHolder).bind(meetingList[position])

    override fun getItemCount(): Int = meetingList.size

    inner class MeetingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val tvTitleItemListMeetings = itemView.findViewById<TextView>(R.id.tvTitleItemListMeetings)
        private val tvDateItemListMeetings = itemView.findViewById<TextView>(R.id.tvDateItemListMeetings)
        private val tvTimeItemListMeetings = itemView.findViewById<TextView>(R.id.tvTimeItemListMeetings)
        private val tvNameItemListMeetings = itemView.findViewById<TextView>(R.id.tvNameItemListMeetings)
        private val tvEmailItemListMeetings = itemView.findViewById<TextView>(R.id.tvEmailItemListMeetings)
        private val imgItemListMeetings = itemView.findViewById<ImageView>(R.id.imgItemListMeetings)

        fun bind(meeting: MeetingEntity){
            tvDateItemListMeetings.text = "${itemView.context.getString(R.string.date)}: ${meeting.date}"
            tvEmailItemListMeetings.text = meeting.email
            tvNameItemListMeetings.text = meeting.userName
            tvTitleItemListMeetings.text = meeting.title
            tvTimeItemListMeetings.text = "${itemView.context.getString(R.string.time)}: ${meeting.time}"
            Picasso.get()
                .load(meeting.img)
                .error(ContextCompat.getDrawable(itemView.context, R.drawable.ic_error)!!)
                .fit()
                .centerCrop()
                .into(imgItemListMeetings)
        }
    }
}