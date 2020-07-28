package com.eiffelyk.ft_login.model

import androidx.databinding.BaseObservable
import com.eiffelyk.ft_login.BR

class LoginLayoutBean : BaseObservable() {
    var isLoginLayout: Boolean = true
        set(value) {
            if (value == field) {
                return
            }
            field = value
            notifyPropertyChanged(BR._all)
        }
    var tips: String = "登录"
        get() {
            field = if (isLoginLayout) {
                "登录"
            } else {
                "注册"
            }
            return field
        }
    var btnText: String = "登录"
        get() {
            field = if (isLoginLayout) {
                "登录"
            } else {
                "注册"
            }
            return field
        }
    var futureName: String = "我要注册"
        get() {
            field = if (isLoginLayout) {
                "我也注册"
            } else {
                "我有账号，直接登录"
            }
            return field
        }
}