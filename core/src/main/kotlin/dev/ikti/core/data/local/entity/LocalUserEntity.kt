package dev.ikti.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class LocalUserEntity(
    @PrimaryKey @ColumnInfo(name = "token") val token: String,
    @ColumnInfo(name = "akun") val akun: String,
    @ColumnInfo(name = "pegawai") val pegawai: String,
    @ColumnInfo(name = "nama") val nama: String,
    @ColumnInfo(name = "nip") val nip: String,
    @ColumnInfo(name = "role") val role: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "telepon") val telepon: String,
    @ColumnInfo(name = "foto") val foto: String,
    @ColumnInfo(name = "alamat") val alamat: String,
    @ColumnInfo(name = "alamat_lat") val alamatLat: Double,
    @ColumnInfo(name = "alamat_lon") val alamatLon: Double,
    @ColumnInfo(name = "foto_pegawai") val fotoPegawai: String
)
