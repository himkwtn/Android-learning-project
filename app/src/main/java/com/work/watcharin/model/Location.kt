package com.work.watcharin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(val lat: Double, val lon: Double, val name: String, val id: String) : Parcelable