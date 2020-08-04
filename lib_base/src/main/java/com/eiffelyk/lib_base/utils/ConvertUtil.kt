package com.eiffelyk.lib_base.utils

import android.text.TextUtils
import com.eiffelyk.lib_base.model.DataBean

object ConvertUtil {
    @JvmStatic
    fun convertName(bean: DataBean): String {
        return if (TextUtils.isEmpty(bean.author)) {
            "推荐：${bean.shareUser}"
        } else {
            "作者：${bean.author}"
        }
    }
}