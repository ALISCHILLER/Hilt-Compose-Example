package com.msa.hilt_compose_example.di



import android.content.Context
import android.content.SharedPreferences
import com.msa.hilt_compose_example.utils.PreferenceKeys
import com.msa.hilt_compose_example.utils.PreferenceUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    /**
     * این متد یک نمونه از SharedPreferences را فراهم می‌کند.
     * SharedPreferences برای ذخیره‌سازی داده‌های ساده و کوچک استفاده می‌شود.
     */
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(PreferenceKeys.PREF_NAME, Context.MODE_PRIVATE)
    }

    /**
     * این متد می‌تواند برای ذخیره‌سازی و بازیابی داده‌ها از SharedPreferences استفاده شود.
     * می‌توانید برای داده‌های مختلف مانند توکن‌های احراز هویت و تنظیمات کاربر استفاده کنید.
     */
    @Provides
    @Singleton
    fun providePreferenceUtils(sharedPreferences: SharedPreferences): PreferenceUtils {
        return PreferenceUtils(sharedPreferences)
    }
}
