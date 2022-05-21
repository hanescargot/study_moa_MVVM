package com.pyrion.studymoa.utils

import com.google.gson.annotations.SerializedName

class AddressListDTO {
    @SerializedName(value="places")
    private val placesList:ArrayList<String> = ArrayList();

    fun add(address : String){
        this.placesList.add(address)
    }
    fun addAll(addresses : ArrayList<String> ){
        this.placesList.addAll(addresses)
    }
}