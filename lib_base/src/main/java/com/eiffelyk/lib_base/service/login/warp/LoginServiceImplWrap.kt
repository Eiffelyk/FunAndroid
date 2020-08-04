package com.eiffelyk.lib_base.service.login.warp

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.eiffelyk.lib_base.model.User
import com.eiffelyk.lib_base.service.ConstantsPath
import com.eiffelyk.lib_base.service.login.LoginService

object LoginServiceImplWrap {
    @Autowired(name = ConstantsPath.LOGIN_SERVICE_PATH)
    lateinit var service: LoginService

    init {
        ARouter.getInstance().inject(this)
    }

    fun start(context: Context): LiveData<User> {
        return service.start(context)
    }

    fun isLogin(): Boolean {
        return service.isLogin()
    }

    fun getUserInfo(): User? {
        return service.getUserInfo()
    }

    fun removeUserInfo(): Unit {
        service.removeUsrInfo()
    }

    fun getLiveData(): LiveData<User> {
        return service.getLiveData()
    }
}