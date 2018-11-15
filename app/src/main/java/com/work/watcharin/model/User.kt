package com.work.watcharin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String = "",
    val uid: String = "",
    val profileImg: String = "",
    val stats: UserStat = UserStat(),
    val parties: List<String> = listOf(),
    val location: Location? = null
): Parcelable