package dev.ikti.kehadiran.data.model

import com.google.gson.annotations.SerializedName

data class FotoPegawaiResponse(
    @SerializedName("id_pegawai")
    val pegawai: String,
    @SerializedName("foto")
    val foto: String
)
