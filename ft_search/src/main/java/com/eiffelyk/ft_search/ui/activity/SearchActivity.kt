package com.eiffelyk.ft_search.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.eiffelyk.ft_search.R
import com.eiffelyk.ft_search.databinding.ActivitySearchBinding
import com.eiffelyk.ft_search.ui.fragment.HotKeyFragment
import com.eiffelyk.ft_search.ui.fragment.SearchResultFragment
import com.eiffelyk.ft_search.ui.viewmodel.SearchViewModel
import com.eiffelyk.lib_base.base.BaseActivity
import com.eiffelyk.lib_base.utils.KeyBoardUtils

class SearchActivity : BaseActivity<SearchViewModel, ActivitySearchBinding>() {

    override fun getLayoutResId(): Int = R.layout.activity_search

    override fun initView() {
        addFragment(HotKeyFragment.newInstance(), "HotKeyFragment")
        mViewDataBinding.searchTextCancel.setOnClickListener { finish() }
        mViewDataBinding.searchEdit.requestFocus()
        mViewDataBinding.searchEdit.setOnEditorActionListener(object :
            TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                    if (getInputText() == null) {
                        return true
                    }
                    mViewDataBinding.searchEdit.clearFocus()
                    KeyBoardUtils.hideKeyBoard(mViewDataBinding.searchEdit)
                    addFragment(
                        SearchResultFragment.newInstance(getInputText()!!),"SearchResultFragment"
                    )
                    return true
                }
                return false
            }

        })
    }

    override fun initData() {
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SearchActivity::class.java))
        }
    }

    private fun getInputText(): String? {
        val input = mViewDataBinding.searchEdit.text.toString().trim()
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "please input words", Toast.LENGTH_SHORT).show()
            return null
        }
        return input
    }

    fun setSearchEditData(key: String) {
        mViewDataBinding.searchEdit.setText(key)
    }

    private fun addFragment(fragment: Fragment, tag: String): Unit {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.search_frame, fragment, tag)
        beginTransaction.commit()
    }
}