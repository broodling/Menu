package com.android.domain

import com.android.data.repository.MenuRepository
import com.android.data.storage.UserPreferencesRepository
import com.android.domain.base.BaseUseCase
import com.android.domain.params.LoginUserParams
import com.android.model.post.UserPost

class LoginUserUseCase(
    private val menuRepository: MenuRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) :
    BaseUseCase<LoginUserParams, String>() {

    operator fun invoke(params: LoginUserParams) = asFlowWithStates {
        menuRepository.loginUser(UserPost(email = params.email, password = params.password)).also {
            userPreferencesRepository.setAccessToken(it)
        }
    }

}