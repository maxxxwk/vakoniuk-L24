package com.maxxxwk.architecturel24.data.repository

class UserStatusRepositoryImpl : UserStatusRepository {
    override fun getBannedUsersIdList(): List<Int> {
        return listOf(7)
    }

    override fun getUsersWithWarningsIdList(): List<Int> {
        return listOf(3, 4)
    }
}