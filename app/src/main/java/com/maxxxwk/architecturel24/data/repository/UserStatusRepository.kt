package com.maxxxwk.architecturel24.data.repository

interface UserStatusRepository {
    fun getBannedUsersIdList(): List<Int>
    fun getUsersWithWarningsIdList(): List<Int>
}