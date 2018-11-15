package com.work.watcharin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Party(
    val members: List<String> = listOf(),
    val topic: String = "",
    val code: String = "",
    val chat: Chat = Chat(),
    val location: Location? = null,
    val time: Date? = null
): Parcelable