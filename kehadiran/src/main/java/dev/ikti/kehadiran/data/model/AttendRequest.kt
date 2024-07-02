package dev.ikti.kehadiran.data.model

import com.google.gson.annotations.SerializedName

data class AttendRequest(
    @SerializedName("id_pegawai")
    val pegawai: String,
    @SerializedName("id_jadwal_pegawai")
    val jadwal: String,
    @SerializedName("tanggal")
    val tanggal: String,
    @SerializedName("foto")
    val foto: String
)
