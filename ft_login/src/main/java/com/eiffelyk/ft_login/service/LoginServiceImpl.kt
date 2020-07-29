package com.eiffelyk.ft_login.service

import android.content.Context
import androidx.lifecycle.LiveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.eiffelyk.ft_login.UserManager
import com.eiffelyk.lib_base.model.User
import com.eiffelyk.lib_base.service.ConstantsPath
import com.eiffelyk.lib_base.service.login.LoginService

@Route(path = ConstantsPath.LOGIN_SERVICE_PATH)
class LoginServiceImpl : LoginService {
    override fun start(context: Context): LiveData<User> {
        return UserManager.start(context)
    }

    override fun isLogin(): Boolean {
        return UserManager.isLogin()
    }

    override fun getUserInfo(): User? {
        return UserManager.getUser()
    }

    override fun removeUsrInfo() {
        UserManager.removeUser()
    }

    override fun getLiveData(): LiveData<User> {
        return UserManager.getLoginLiveData()
    }

    override fun init(context: Context?) {
    }
}