package com.pyrion.studymoa.utils

import com.google.gson.annotations.SerializedName

class RequestStudyDTO {
    @SerializedName(value="places")
    private val placesList:ArrayList<String> = ArrayList();

    fun add(place : String){
        this.placesList.add(place)
    }
}