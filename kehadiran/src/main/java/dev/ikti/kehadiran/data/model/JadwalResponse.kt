package dev.ikti.kehadiran.data.model

import com.google.gson.annotations.SerializedName

data class JadwalResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("id_pegawai")
    val pegawai: String,
    @SerializedName("id_hari")
    val hari: Int,
    @SerializedName("id_shift")
    val shift: String,
    @SerializedName("jam_masuk")
    val masuk: String,
    @SerializedName("jam_pulang")
    val pulang: String
)
