package com.android.domain

import com.android.data.repository.MenuRepository
import com.android.domain.base.BaseParams
import com.android.domain.base.BaseUseCase
import kotlinx.coroutines.flow.first

class IsUserLoggedInUseCase(private val menuRepository: MenuRepository) :
    BaseUseCase<BaseParams, String>() {

    operator fun invoke() = asFlow {
        menuRepository.isUserLoggedIn().first()
    }

}