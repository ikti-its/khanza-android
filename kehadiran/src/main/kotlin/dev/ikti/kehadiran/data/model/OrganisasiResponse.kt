package dev.ikti.kehadiran.data.model

import com.google.gson.annotations.SerializedName

data class OrganisasiResponse(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
