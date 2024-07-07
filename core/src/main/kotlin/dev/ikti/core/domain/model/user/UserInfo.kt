package dev.ikti.core.domain.model.user

import androidx.room.ColumnInfo

data class UserInfo(
    @ColumnInfo(name = "akun") val akun: String,
    @ColumnInfo(name = "nama") val nama: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "role") val role: String,
    @ColumnInfo(name = "telepon") val telepon: String,
    @ColumnInfo(name = "foto") val foto: String,
    @ColumnInfo(name = "alamat") val alamat: String,
    @ColumnInfo(name = "alamat_lat") val alamatLat: Double,
    @ColumnInfo(name = "alamat_lon") val alamatLon: Double,
)
