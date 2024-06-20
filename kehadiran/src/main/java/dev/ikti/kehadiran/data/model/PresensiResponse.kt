package dev.ikti.kehadiran.data.model

import com.google.gson.annotations.SerializedName

data class PresensiResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("id_pegawai")
    val pegawai: String,
    @SerializedName("tanggal")
    val tanggal: String,
    @SerializedName("jam_masuk")
    val masuk: String,
    @SerializedName("jam_pulang")
    val pulang: String?,
    @SerializedName("keterangan")
    val keterangan: String?,
    @SerializedName("foto")
    val foto: String?
)
