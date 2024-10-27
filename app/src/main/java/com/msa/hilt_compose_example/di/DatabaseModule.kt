package com.msa.hilt_compose_example.di


import android.content.Context
import androidx.room.Room
import com.msa.hilt_compose_example.data.local.AppDatabase
import com.msa.hilt_compose_example.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * این متد یک نمونه از AppDatabase را فراهم می‌کند.
     * با استفاده از Room پایگاه داده محلی ایجاد می‌شود.
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database" // نام پایگاه داده
        ).fallbackToDestructiveMigration() // در صورت تغییر در ساختار پایگاه داده، داده‌های قدیمی حذف می‌شوند
            .build()
    }

    /**
     * این متد یک نمونه از UserDao را فراهم می‌کند.
     * با استفاده از این DAO می‌توانید به داده‌های کاربر در پایگاه داده دسترسی پیدا کنید.
     */
    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao() // دسترسی به UserDao از AppDatabase
    }
}
