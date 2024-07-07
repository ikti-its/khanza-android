package dev.ikti.pegawai.data.model

import com.google.gson.annotations.SerializedName

data class KetersediaanResponse(
    @SerializedName("nama")
    val nama: String,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("nip")
    val nip: String,
    @SerializedName("telepon")
    val telepon: String,
    @SerializedName("jabatan")
    val jabatan: String,
    @SerializedName("departemen")
    val departemen: String,
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("available")
    val available: Boolean
)
