package com.maxxxwk.architecturel24.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserStatusRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getBannedUsersIdList() = withContext(dispatcher) {
        listOf(7)
    }

    suspend fun getUsersWithWarningsIdList() = withContext(dispatcher) {
        listOf(3, 4)
    }

}
