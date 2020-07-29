package com.eiffelyk.ft_login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eiffelyk.ft_login.ui.LoginActivity
import com.eiffelyk.lib_base.model.User
import com.tencent.mmkv.MMKV

object UserManager {
    private const val USER_DATA: String = "user_data"
    private val mmkv = MMKV.defaultMMKV()
    private val liveData = MutableLiveData<User>()
    fun getLoginLiveData(): MutableLiveData<User> {
        return liveData
    }

    fun getUser(): User? {
        return mmkv.decodeParcelable(USER_DATA, User::class.java)
    }

    fun setUser(user: User?) {
        mmkv.encode(USER_DATA, user)
        if (liveData.hasObservers()) {
            liveData.postValue(user)
        }
    }

    fun isLogin(): Boolean {
        return getUser() != null
    }

    fun removeUser() {
        mmkv.removeValueForKey(USER_DATA)
    }

    fun start(context: Context): LiveData<User> {
        context.startActivity(Intent(context, LoginActivity::class.java))
        return liveData
    }
}