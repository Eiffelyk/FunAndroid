package com.eiffelyk.ft_login.ui

import androidx.lifecycle.Observer
import com.eiffelyk.ft_login.R
import com.eiffelyk.ft_login.databinding.ActivityLoginBinding
import com.eiffelyk.ft_login.model.LoginLayoutBean
import com.eiffelyk.lib_base.base.BaseActivity
import com.eiffelyk.lib_base.model.User

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    private lateinit var layoutBean: LoginLayoutBean
    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun initData() {
        layoutBean = LoginLayoutBean()
        mViewDataBinding.bean = layoutBean
    }

    override fun initView() {
        mViewDataBinding.loginBtnBack.setOnClickListener { finish() }
        mViewDataBinding.loginBtnFuture.setOnClickListener {
            layoutBean.isLoginLayout = !layoutBean.isLoginLayout
            initEditStatus()
        }
        mViewDataBinding.loginBtnDone.setOnClickListener {
            if (layoutBean.isLoginLayout) {
                doLogin()
            } else {
                doRegister()
            }
        }
    }

    private fun doLogin() {
        mViewModel.login(getUserName(), getPassword()).observe(this, Observer { user ->
            saveUserInfo(user)
        })
    }

    private fun doRegister() {
        mViewModel.register(getUserName(), getPassword(), getRePassword())
            .observe(this, Observer { user -> saveUserInfo(user) })
    }

    private fun saveUserInfo(user: User) {

    }

    private fun getRePassword(): String {
        return mViewDataBinding.editUserName.text.toString().trim()
    }

    private fun getPassword(): String {
        return mViewDataBinding.editPassword.text.toString().trim()
    }

    private fun getUserName(): String {
        return mViewDataBinding.editRePassword.text.toString().trim()
    }

    private fun initEditStatus() {
        mViewDataBinding.editUserName.text = null
        mViewDataBinding.editPassword.text = null
        mViewDataBinding.editRePassword.text = null
    }
}