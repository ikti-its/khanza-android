package dev.ikti.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.ikti.core.data.local.dao.LocalUserDao
import dev.ikti.core.data.local.entity.LocalUserEntity

@Database(
    entities = [LocalUserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localUserDao(): LocalUserDao
}
