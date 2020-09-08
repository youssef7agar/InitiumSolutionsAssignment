package com.example.initiumsolutionsassignment.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

sealed class UserStatus() {
    data class LoggedIn(val firstName: String, val lastName: String) : UserStatus()
    object LoggedOut : UserStatus()
}
interface UserStatusChange {
    fun onStatusChange(status: UserStatus)
}