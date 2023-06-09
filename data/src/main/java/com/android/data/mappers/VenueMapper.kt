package com.android.data.mappers

import com.android.model.local.Venue
import com.android.model.remote.venue.VenueInfoRemote

class VenueMapper : BaseMapper<List<VenueInfoRemote>?, List<Venue>> {

    override fun map(input: List<VenueInfoRemote>?): List<Venue> {
        val venueList: MutableList<Venue> = mutableListOf()
        input?.let { venueRemoteList ->
            for (venueInfo in venueRemoteList) {
                venueList.add(
                    Venue(
                        id = venueInfo.venue?.id ?: Int.MAX_VALUE,
                        address = venueInfo.venue?.address ?: "",
                        name = venueInfo.venue?.name ?: "",
                        isOpen = venueInfo.venue?.isOpen ?: false,
                        image = venueInfo.venue?.image?.thumbnailMedium ?: "",
                        city = venueInfo.venue?.city ?: "",
                        description = venueInfo.venue?.description ?: "",
                        welcomeMessage = venueInfo.venue?.welcomeMessage ?: "",
                        distance = venueInfo.distance ?: Double.MAX_VALUE
                    )
                )
            }
        }
        return venueList
    }
}