package com.maxxxwk.architecturel24.data.repository

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import javax.inject.Inject

class AndroidResourcesRepository @Inject constructor(private val context: Context) {
    fun getColor(@ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)
}