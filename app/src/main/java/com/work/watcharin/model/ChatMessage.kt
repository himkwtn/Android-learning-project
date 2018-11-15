package com.work.watcharin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ChatMessage(
    val content: String = "",
    val message_id : String = "",
    val time: Date = Date(),
    val author: User = User()
): Parcelable