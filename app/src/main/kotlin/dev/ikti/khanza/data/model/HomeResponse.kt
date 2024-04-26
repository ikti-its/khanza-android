package dev.ikti.khanza.data.model

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("akun")
    val akun: String,
    @SerializedName("pegawai")
    val pegawai: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("nip")
    val nip: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("telepon")
    val telepon: String,
    @SerializedName("profil")
    val profil: String,
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("alamat_lat")
    val alamatLat: Float,
    @SerializedName("alamat_lon")
    val alamatLon: Float,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("shift")
    val shift: String,
    @SerializedName("jam_masuk")
    val jamMasuk: String,
    @SerializedName("jam_pulang")
    val jamPulang: String,
    @SerializedName("status")
    val status: Boolean
)