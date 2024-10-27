package com.msa.hilt_compose_example.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

import okhttp3.Request
import okio.IOException


class AuthInterceptor @Inject constructor(
    private val tokenProvider: TokenProvider // تزریق TokenProvider
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        // دریافت توکن از TokenProvider
        val token = tokenProvider.getToken()

        // ساخت درخواست اصلی
        val originalRequest = chain.request()

        // ساخت یک درخواست جدید و اضافه کردن توکن به هدر آن
        val requestBuilder: Request.Builder = originalRequest.newBuilder().apply {
            token?.let { // اگر توکن وجود دارد، آن را به هدر اضافه کن
                header("Authorization", "Bearer $it")
            }
        }

        // ساخت درخواست جدید
        val request = requestBuilder.build()
        // ارسال درخواست و دریافت پاسخ
        return chain.proceed(request)
    }
}
