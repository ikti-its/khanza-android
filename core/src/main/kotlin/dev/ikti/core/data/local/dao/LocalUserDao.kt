package dev.ikti.core.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.domain.model.user.UserLocation

@Dao
interface LocalUserDao : BaseDao<LocalUserEntity> {
    @Query("SELECT * FROM user WHERE akun = :akun")
    suspend fun getLocalUser(akun: String): LocalUserEntity

    @Query("SELECT akun, pegawai, nama, nip, email, telepon, foto_pegawai FROM user WHERE akun = :akun")
    suspend fun getLocalUserInfo(akun: String): UserInfo

    @Query("SELECT alamat, alamat_lat, alamat_lon FROM user WHERE akun = :akun")
    suspend fun getLocalUserLocation(akun: String): UserLocation

    @Query("SELECT foto_pegawai FROM user WHERE akun = :akun")
    suspend fun getLocalUserPhoto(akun: String): String
}
