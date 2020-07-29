package com.eiffelyk.lib_base.service.login

import android.content.Context
import androidx.lifecycle.LiveData
import com.alibaba.android.arouter.facade.template.IProvider
import com.eiffelyk.lib_base.model.User

interface LoginService : IProvider {
    fun start(context: Context): LiveData<User>
    fun isLogin(): Boolean
    fun getUserInfo(): User?
    fun removeUsrInfo()
    fun getLiveData(): LiveData<User>
}