package com.android.domain

import com.android.data.repository.MenuRepository
import com.android.domain.base.BaseUseCase
import com.android.domain.params.GetVenuesParams
import com.android.model.local.Venue
import com.android.model.post.LocationPost

class GetVenuesUseCase(private val repository: MenuRepository) :
    BaseUseCase<GetVenuesParams, List<Venue>>() {

    operator fun invoke(params: GetVenuesParams) = asFlowWithStates {
        repository.getVenues(LocationPost(params.latitude, params.longitude))
    }
}