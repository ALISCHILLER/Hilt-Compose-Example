package com.msa.hilt_compose_example.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.msa.hilt_compose_example.data.local.dao.UserDao
import com.msa.hilt_compose_example.data.model.User

/**
 * این کلاس نماینده پایگاه داده است که شامل موجودیت‌ها و DAO‌ها می‌باشد.
 * با استفاده از Room این پایگاه داده مدیریت می‌شود.
 */
@Database(
    entities = [User::class], // موجودیت‌های مورد نظر برای پایگاه داده
    version = 1, // نسخه پایگاه داده
    exportSchema = false // تنظیمات برای صادرات اسکیما
)
@TypeConverters(User::class) // اگر از نوع‌های سفارشی استفاده می‌کنید
abstract class AppDatabase : RoomDatabase() {

    /**
     * این متد یک نمونه از UserDao را فراهم می‌کند.
     */
    abstract fun userDao(): UserDao
}