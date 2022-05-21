package com.pyrion.studymoa.utils

class ResponseAddStudyDTO(
    private  var errorCode : String,
    private  var statusCode : Number,
    private  var errorMessage : String
) {
    fun getErrorMsg(): String {
        return this.errorMessage
    }
    fun getStatusCode(): Number {
        return this.statusCode
    }
}

//{
//    "errorCode": 2,
//    "statusCode": 400,
//    "errorMessage": "연락처는 이메일, 전화번호만 허용합니다. (010-xxxx-xxxx or email@gmail.com)"
//}