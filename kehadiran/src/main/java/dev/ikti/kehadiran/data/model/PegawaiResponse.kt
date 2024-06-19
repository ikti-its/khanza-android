package dev.ikti.kehadiran.data.model

import com.google.gson.annotations.SerializedName

data class PegawaiResponse(
    @SerializedName("nama")
    val nama: String
)
