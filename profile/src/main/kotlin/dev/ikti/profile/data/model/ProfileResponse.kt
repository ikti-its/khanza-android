package dev.ikti.profile.data.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("akun")
    val akun: String,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("alamat_lat")
    val alamatLat: Double,
    @SerializedName("alamat_lon")
    val alamatLon: Double
)
