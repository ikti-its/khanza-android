package dev.ikti.kehadiran.data.model

import com.google.gson.annotations.SerializedName

data class StatusPresensiResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("id_pegawai")
    val pegawai: String
)
