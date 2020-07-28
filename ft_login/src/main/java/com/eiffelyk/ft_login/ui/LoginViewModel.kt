package com.eiffelyk.ft_login.ui

import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eiffelyk.lib_base.model.User
import com.eiffelyk.lib_base.utils.BaseContext
import com.eiffelyk.lib_net.model.NetResult
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private val loginLiveData = MediatorLiveData<User>()
    fun login(userName: String, password: String): MediatorLiveData<User> {
        viewModelScope.launch {
            val result = loginRepository.login(userName, password)
            if (result is NetResult.Success) {
                loginLiveData.postValue(result.data)
            } else if (result is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    result.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return loginLiveData
    }

    fun register(userName: String, password: String, rePassword: String): MediatorLiveData<User> {
        viewModelScope.launch {
            val result = loginRepository.register(userName, password, rePassword)
            if (result is NetResult.Success) {
                loginLiveData.postValue(result.data)
            } else if (result is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    result.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return loginLiveData
    }
}