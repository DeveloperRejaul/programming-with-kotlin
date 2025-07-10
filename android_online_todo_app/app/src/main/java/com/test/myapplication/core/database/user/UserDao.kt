package com.test.myapplication.core.database.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user_table LIMIT 1")
    suspend fun getUser(): UserEntity?
}