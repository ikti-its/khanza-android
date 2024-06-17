package dev.ikti.kehadiran.data.model

import com.google.gson.annotations.SerializedName

data class PengajuanRequest(
    @SerializedName("id_pegawai")
    val pegawai: String,
    @SerializedName("tanggal_mulai")
    val mulai: String,
    @SerializedName("tanggal_selesai")
    val selesai: String,
    @SerializedName("id_alasan_cuti")
    val alasan: String,
    @SerializedName("status")
    val status: String
)
