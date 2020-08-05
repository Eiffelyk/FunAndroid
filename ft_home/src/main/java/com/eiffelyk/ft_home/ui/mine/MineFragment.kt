package com.eiffelyk.ft_home.ui.mine

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.databinding.FragmentMineBinding
import com.eiffelyk.lib_base.base.BaseFragment
import com.eiffelyk.lib_base.service.aboutus.warp.AboutUsServiceImplWrap
import com.eiffelyk.lib_base.service.login.warp.LoginServiceImplWrap

class MineFragment : BaseFragment<MineViewModel, FragmentMineBinding>() {

    private lateinit var mineViewModel: MineViewModel
    override fun getLayoutResId(): Int = R.layout.fragment_mine

    override fun initData() {
        mViewDataBinding.user = LoginServiceImplWrap.getUserInfo()
    }

    override fun iniView() {
        mViewDataBinding.fragmentMineLogin.setOnClickListener {
            if (!LoginServiceImplWrap.isLogin()) {
                LoginServiceImplWrap.start(requireContext())
            }
        }
        mViewDataBinding.fragmentMineAboutLayout.setOnClickListener {
            AboutUsServiceImplWrap.start(
                requireContext()
            )
        }
        LoginServiceImplWrap.getLiveData()
            .observe(this, Observer { user -> mViewDataBinding.user = user })
        mViewDataBinding.fragmentMineLogout.setOnClickListener {
            AlertDialog.Builder(requireContext()).setTitle("提示").setMessage("确定退出？")
                .setPositiveButton("确定") { dialog, _ ->
                    dialog.dismiss()
                    LoginServiceImplWrap.removeUserInfo()
                    initData()
                }.setNegativeButton("取消") { dialog, _ -> dialog.dismiss() }.show()
        }
    }

}