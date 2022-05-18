package com.pyrion.studymoa.utils

import com.google.gson.annotations.SerializedName

class StudyDTO(
    @SerializedName(value="imageURL")
    var imageURL : String,
    @SerializedName(value="studyTitle")
    var studyTitle : String,
    @SerializedName(value="contact")
    var contact : String,
    @SerializedName(value="place")
    var place : String,
    @SerializedName(value="studyDescription")
    var studyDescription : String
) {

}