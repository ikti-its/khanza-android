package dev.ikti.core.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.model.user.UserLocation

@Dao
interface LocalUserDao : BaseDao<LocalUserEntity> {
    @Query("SELECT * FROM user WHERE nip = :nip")
    suspend fun getLocalUser(nip: String): LocalUserEntity

    @Query("SELECT alamat_lat AS latitude, alamat_lon AS longitude FROM user WHERE nip = :nip")
    suspend fun getLocalUserLocation(nip: String): UserLocation

    @Query("SELECT telepon FROM user WHERE nip = :nip")
    suspend fun getLocalUserPhone(nip: String): String

    @Query("SELECT foto FROM user WHERE nip = :nip")
    suspend fun getLocalUserPhoto(nip: String): String
}
