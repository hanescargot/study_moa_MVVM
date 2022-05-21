package com.pyrion.studymoa.utils

import com.google.gson.annotations.SerializedName

class MyStudyDTO (
    @SerializedName(value="id")
    var id : String,
    @SerializedName(value="image_url")
    var imgUrl : String,
    @SerializedName(value="study_title")
    var title : String,
    @SerializedName(value="contact")
    var contact : String,
    @SerializedName(value="place")
    var address : String,
    @SerializedName(value="study_description")
    var description : String
) {

}