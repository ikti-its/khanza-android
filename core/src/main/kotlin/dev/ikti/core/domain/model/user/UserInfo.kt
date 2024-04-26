package dev.ikti.core.domain.model.user

import androidx.room.ColumnInfo

data class UserInfo(
    @ColumnInfo(name = "akun") val akun: String,
    @ColumnInfo(name = "pegawai") val pegawai: String,
    @ColumnInfo(name = "nama") val nama: String,
    @ColumnInfo(name = "nip") val nip: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "telepon") val telepon: String,
    @ColumnInfo(name = "foto_pegawai") val foto: String,
)
