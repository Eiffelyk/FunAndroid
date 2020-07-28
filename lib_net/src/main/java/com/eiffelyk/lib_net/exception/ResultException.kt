package com.eiffelyk.lib_net.exception

class ResultException(var errorCode: String?, var errorMsg: String?) : Exception(errorMsg)