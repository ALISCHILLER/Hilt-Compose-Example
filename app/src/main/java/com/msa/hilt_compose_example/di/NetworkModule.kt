package com.msa.hilt_compose_example.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.msa.hilt_compose_example.data.remote.ApiService
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.msa.hilt_compose_example.data.remote.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * این متد یک نمونه از HttpLoggingInterceptor را فراهم می‌کند
     * برای لاگ‌گیری درخواست‌ها و پاسخ‌ها در هنگام توسعه.
     * این ابزار به توسعه‌دهندگان کمک می‌کند تا خطاها و مشکلات شبکه را شناسایی کنند.
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // سطح لاگ‌گیری را به BODY تنظیم می‌کند
        }
        return loggingInterceptor
    }

    /**
     * این متد یک نمونه از ChuckerInterceptor را فراهم می‌کند
     * برای نمایش درخواست‌ها و پاسخ‌های شبکه در یک UI بصری.
     * این ابزار به توسعه‌دهندگان کمک می‌کند تا درخواست‌ها را در حین اجرا بررسی کنند.
     */
    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250_000L) // حداکثر طول محتوا که باید ذخیره شود
            .redactHeaders(listOf("Authorization", "Cookie")) // هدرهای حساس را مخفی می‌کند
            .build()
    }

    /**
     * این متد یک نمونه از OkHttpClient را فراهم می‌کند.
     * AuthInterceptor و HttpLoggingInterceptor به آن اضافه می‌شوند.
     * این کلاینت برای مدیریت درخواست‌ها و پاسخ‌های HTTP استفاده می‌شود.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor, // به عنوان مثال، یک AuthInterceptor برای احراز هویت
        loggingInterceptor: HttpLoggingInterceptor, // برای لاگ‌گیری
        chuckerInterceptor: ChuckerInterceptor // برای لاگ‌گیری بصری
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor) // افزودن AuthInterceptor برای افزودن توکن به درخواست‌ها
            .addInterceptor(loggingInterceptor) // افزودن HttpLoggingInterceptor برای لاگ‌گیری
            .addInterceptor(chuckerInterceptor) // افزودن ChuckerInterceptor برای لاگ‌گیری بصری
            .retryOnConnectionFailure(true) // فعال کردن تلاش مجدد در صورت شکست اتصال
            .connectTimeout(30, TimeUnit.SECONDS) // زمان اتصال
            .readTimeout(30, TimeUnit.SECONDS) // زمان خواندن
            .build()
    }

    /**
     * این متد یک نمونه از Retrofit را فراهم می‌کند.
     * OkHttpClient و GsonConverterFactory به آن اضافه می‌شوند.
     * Retrofit به شما امکان می‌دهد که درخواست‌های HTTP را به سادگی مدیریت کنید.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://your-api-url.com/") // URL پایه API شما
            .client(okHttpClient) // کلاینت OkHttp را به Retrofit اضافه می‌کند
            .addConverterFactory(GsonConverterFactory.create()) // تبدیل‌کننده JSON به مدل
            .addConverterFactory(MoshiConverterFactory.create()) // می‌توانید Moshi را نیز به عنوان تبدیل‌کننده اضافه کنید
            .build()
    }

    /**
     * این متد ApiService را فراهم می‌کند.
     * ApiService ابزار اصلی برای انجام درخواست‌های API است.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java) // ایجاد ApiService از Retrofit
    }
}
