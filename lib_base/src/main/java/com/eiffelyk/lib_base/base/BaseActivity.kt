package com.eiffelyk.lib_base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.databinding.ViewDataBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<T : ViewModel, M : ViewDataBinding> : AppCompatActivity() {
    lateinit var mViewModel: T
    lateinit var mViewDataBinding: M
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        StatusBarKt.fitSystemBar(this)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutResId())
        initViewModel()
        initView()
        initData()
    }

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()

    private fun initViewModel(){
//        val clazz =
//            this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<T>
//        mViewModel = getViewModel<T>(clazz) //koin 注入
        val clazz = this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier as KClass<T>
        mViewModel = getViewModel<T>(clazz)
    }
}