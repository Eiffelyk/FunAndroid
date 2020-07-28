package com.eiffelyk.lib_net.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import javax.net.ssl.SSLHandshakeException

object DealException {
    fun handler(t: Throwable): ResultException {
        val exception:ResultException
        if (t is ResultException){
            exception = t
        }else if (t is HttpException){
            exception = when(t.code()){
                ApiResultCode.UNAUTHORIZED,
                ApiResultCode.FORBIDDEN,
                    //权限错误，需要实现
                ApiResultCode.NOT_FOUND -> ResultException(
                    t.code().toString(),
                    "网络错误"
                )
                ApiResultCode.REQUEST_TIMEOUT,
                ApiResultCode.GATEWAY_TIMEOUT -> ResultException(
                    t.code().toString(),
                    "网络连接超时"
                )
                ApiResultCode.INTERNAL_SERVER_ERROR,
                ApiResultCode.BAD_GATEWAY,
                ApiResultCode.SERVICE_UNAVAILABLE -> ResultException(
                    t.code().toString(),
                    "服务器错误"
                )
                else -> ResultException(t.code().toString(), "网络错误")
            }
        }else if (t is JsonParseException
            || t is JSONException
            || t is ParseException
        ) {
            exception = ResultException(
                ApiResultCode.PARSE_ERROR,
                "解析错误"
            )
        } else if (t is SocketException) {
            exception = ResultException(
                ApiResultCode.REQUEST_TIMEOUT.toString(),
                "网络连接错误，请重试"
            )
        } else if (t is SocketTimeoutException) {
            exception = ResultException(
                ApiResultCode.REQUEST_TIMEOUT.toString(),
                "网络连接超时"
            )
        } else if (t is SSLHandshakeException) {
            exception = ResultException(
                ApiResultCode.SSL_ERROR,
                "证书验证失败"
            )
        } else if (t is UnknownHostException) {
            exception = ResultException(
                ApiResultCode.UNKNOW_HOST,
                "网络错误，请切换网络重试"
            )
        } else if (t is UnknownServiceException) {
            exception = ResultException(
                ApiResultCode.UNKNOW_HOST,
                "网络错误，请切换网络重试"
            )
        } else if (t is NumberFormatException) {
            exception = ResultException(
                ApiResultCode.UNKNOW_HOST,
                "数字格式化异常"
            )
        }else{
            exception = ResultException(ApiResultCode.UNKNOWN,"未知错误")
        }
        return exception
    }
}