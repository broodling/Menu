package com.android.domain

import com.android.data.repository.MenuRepository
import com.android.domain.base.BaseParams
import com.android.domain.base.BaseUseCase

class LogOutUserUseCase(private val menuRepository: MenuRepository) : BaseUseCase<BaseParams, Unit>() {

    operator fun invoke() = asFlowWithStates {
        menuRepository.logoutUser()
    }
}