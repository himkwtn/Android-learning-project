package com.work.watcharin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Chat(
    val messages: List<ChatMessage> = listOf(),
    val currentPoll: Poll? = null,
    val read: List<User>? = listOf()
): Parcelable