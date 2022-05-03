package com.pyrion.studymoa.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class BottomSheetViewModel {
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    val text: LiveData<String> = _text
}