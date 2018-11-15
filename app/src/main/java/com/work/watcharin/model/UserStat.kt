package com.work.watcharin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserStat(
    var successParties: Int = 0,
    var failedParties: Int = 0,
    var joinedParties: Int = 0,
    var declinedParties: Int = 0,
    var missedParties: Int = 0
): Parcelable