package com.msa.hilt_compose_example.utils


import android.content.SharedPreferences

class PreferenceUtils(private val sharedPreferences: SharedPreferences) {

    /**
     * توکن احراز هویت را ذخیره می‌کند.
     */
    fun saveAuthToken(token: String) {
        sharedPreferences.edit().putString(PREF_AUTH_TOKEN, token).apply()
    }

    /**
     * توکن احراز هویت را بازیابی می‌کند.
     */
    fun getAuthToken(): String? {
        return sharedPreferences.getString(PREF_AUTH_TOKEN, null)
    }

    /**
     * برای حذف توکن احراز هویت.
     */
    fun clearAuthToken() {
        sharedPreferences.edit().remove(PREF_AUTH_TOKEN).apply()
    }

    /**
     * دیگر متدهای مربوط به ذخیره‌سازی و بازیابی داده‌ها می‌توانند در اینجا اضافه شوند.
     */

    companion object {
        const val PREF_AUTH_TOKEN = "auth_token"
    }
}
