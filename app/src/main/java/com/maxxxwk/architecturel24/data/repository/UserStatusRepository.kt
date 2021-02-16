package com.maxxxwk.architecturel24.data.repository

import javax.inject.Inject

class UserStatusRepository @Inject constructor() {
    fun getBannedUsersIdList(): List<Int> {
        return listOf(7)
    }

    fun getUsersWithWarningsIdList(): List<Int> {
        return listOf(3, 4)
    }
}