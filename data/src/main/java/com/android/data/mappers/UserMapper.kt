package com.android.data.mappers

import com.android.model.remote.user.UserInfoRemote

class UserMapper : BaseMapper<UserInfoRemote?, String> {

    override fun map(input: UserInfoRemote?): String {
        return input?.data?.token?.value ?: ""
    }
}