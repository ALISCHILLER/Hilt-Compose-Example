package com.msa.hilt_compose_example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msa.hilt_compose_example.data.model.User


/**
 * این کلاس نماینده DAO برای کار با موجودیت User است.
 */
@Dao
interface UserDao {

    /**
     * این متد یک کاربر جدید را به پایگاه داده اضافه می‌کند.
     * @param user کاربری که باید اضافه شود
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE) // در صورت وجود، کاربر موجود را جایگزین می‌کند
    suspend fun insertUser(user: User)

    /**
     * این متد همه کاربران را از پایگاه داده بازیابی می‌کند.
     * @return لیستی از کاربران
     */
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}