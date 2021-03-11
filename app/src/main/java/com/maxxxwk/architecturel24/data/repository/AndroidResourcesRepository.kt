package com.maxxxwk.architecturel24.data.repository

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AndroidResourcesRepository @Inject constructor(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getColor(@ColorRes colorRes: Int) = withContext(dispatcher) {
        ContextCompat.getColor(context, colorRes)
    }

    suspend fun getString(@StringRes stringRes: Int) = withContext(dispatcher) {
        context.getString(stringRes)
    }
}
