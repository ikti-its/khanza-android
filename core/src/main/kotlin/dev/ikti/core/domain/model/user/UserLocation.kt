package dev.ikti.core.domain.model.user

import androidx.room.ColumnInfo

data class UserLocation(
    @ColumnInfo(name = "alamat") val alamat: String,
    @ColumnInfo(name = "alamat_lat") val latitude: Float,
    @ColumnInfo(name = "alamat_lon") val longitude: Float
)
