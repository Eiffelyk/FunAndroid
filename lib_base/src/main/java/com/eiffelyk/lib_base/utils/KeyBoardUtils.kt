package com.eiffelyk.lib_base.utils

import android.content.Context
import android.hardware.input.InputManager
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyBoardUtils {
    fun hideKeyBoard(view: View): Unit {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken,0)
    }

    fun showKeyBoard(view:View){
        val imm =view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm!=null) {
            view.requestFocus()
            imm.showSoftInput(view,0)
        }
    }
}