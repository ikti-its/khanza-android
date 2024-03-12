package dev.ikti.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class LocalUserEntity(
    @PrimaryKey @ColumnInfo(name = "nip") val nip: String,
    @ColumnInfo(name = "alamat_lat") val alamatLat: Double,
    @ColumnInfo(name = "alamat_lon") val alamatLon: Double,
    @ColumnInfo(name = "telepon") val telepon: String,
    @ColumnInfo(name = "foto") val foto: String,
)
