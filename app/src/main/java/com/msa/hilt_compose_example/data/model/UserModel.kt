package com.msa.hilt_compose_example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * این کلاس نماینده موجودیت User در پایگاه داده است.
 */
@Entity(tableName = "users") // نام جدول در پایگاه داده
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // شناسه کاربر
    val name: String, // نام کاربر
    val email: String // ایمیل کاربر
)