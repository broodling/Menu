package com.android.menu.screens.venue.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.menu.R
import com.android.model.local.Venue
import kotlin.time.Duration.Companion.nanoseconds

class VenueListAdapter(private val venueList: List<Venue>, val onClick: (Venue) -> Unit) :
    RecyclerView.Adapter<VenueListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView
        val distance: TextView
        val address: TextView
        val workingTime: TextView

        init {
            name = view.findViewById(R.id.text_name)
            distance = view.findViewById(R.id.text_distance)
            address = view.findViewById(R.id.text_address)
            workingTime = view.findViewById(R.id.text_working_time)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_venue, parent, false)
        )
    }

    override fun getItemCount() = venueList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val venue = venueList[position]
            name.text = venue.name
            distance.text = venue.distance.toString()
            address.text = itemView.context.getString(
                R.string.venue_address_placeholder,
                venue.address,
                venue.city
            )

            itemView.setOnClickListener {
                onClick(venueList[position])
            }
        }
    }
}
