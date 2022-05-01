package com.pyrion.studymoa.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pyrion.studymoa.R
import com.pyrion.studymoa.databinding.ActivityMainBinding
import com.pyrion.studymoa.databinding.ModalBottomSheetContentBinding

class ModalBottomSheet : BottomSheetDialogFragment(){
    private lateinit var binding: ModalBottomSheetContentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.modal_bottom_sheet_content, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }

}