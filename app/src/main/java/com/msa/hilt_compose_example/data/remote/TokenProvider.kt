package com.msa.hilt_compose_example.data.remote

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokenProvider @Inject constructor(
    context: Context
) {
    // نام SharedPreferences
    companion object {
        private const val PREFS_NAME = "secure_prefs"
        private const val KEY_AUTH_TOKEN = "auth_token"
    }

    // ساخت MasterKey برای رمزنگاری
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    // تعریف EncryptedSharedPreferences
    private val sharedPreferences = EncryptedSharedPreferences.create(
        PREFS_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    /**
     * ذخیره توکن
     * @param token توکنی که باید ذخیره شود
     */
    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_AUTH_TOKEN, token).apply()
    }

    /**
     * دریافت توکن
     * @return توکن احراز هویت یا null در صورتی که توکن وجود نداشته باشد
     */
    fun getToken(): String? {
        return sharedPreferences.getString(KEY_AUTH_TOKEN, null)
    }

    /**
     * حذف توکن
     */
    fun clearToken() {
        sharedPreferences.edit().remove(KEY_AUTH_TOKEN).apply()
    }
}

