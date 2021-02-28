package com.maxxxwk.architecturel24.data.repository

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class UserStatusRepository @Inject constructor() {
    fun getBannedUsersIdList(): Single<List<Int>> {
        return Single.just(listOf(7)).subscribeOn(Schedulers.io())
    }

    fun getUsersWithWarningsIdList(): Single<List<Int>> {
        return Single.just(listOf(3, 4)).subscribeOn(Schedulers.io())
    }
}