package dev.ikti.core.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.domain.model.user.UserLocation

@Dao
interface LocalUserDao : BaseDao<LocalUserEntity> {
    @Query("SELECT * FROM user WHERE token = :token")
    suspend fun getLocalUser(token: String): LocalUserEntity

    @Query("SELECT akun, nama, email, role, foto, alamat, alamat_lat, alamat_lon FROM user WHERE token = :token")
    suspend fun getLocalUserInfo(token: String): UserInfo

    @Query("SELECT alamat, alamat_lat, alamat_lon FROM user WHERE token = :token")
    suspend fun getLocalUserLocation(token: String): UserLocation

    @Query("SELECT foto_pegawai FROM user WHERE token = :token")
    suspend fun getLocalUserPhoto(token: String): String
}
