package com.pyrion.studymoa.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pyrion.studymoa.R
import com.pyrion.studymoa.adapter.BottomSheetListViewAdapter
import com.pyrion.studymoa.databinding.FragmentBottomSheetBinding
import com.pyrion.studymoa.utils.StudyDTO
import java.time.Duration

class BottomSheetFragment : BottomSheetDialogFragment(){
    var studyList = arrayListOf<StudyDTO>()
    lateinit var mainActivity: MainActivity
    private lateinit var mcontext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.println(Log.DEBUG, "~!!context!!!!!","dkdjdkl")
        mcontext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo Model에서 Call
        studyList.addAll(listOf(
            StudyDTO("https://kr-mb.theepochtimes.com/assets/uploads/2019/11/a58d75581e050bbc5c6acfe08ad418ff.png",
                "제목 1",
            "message1",
            "주소1",
            "010-xxxx-xxxx"
            ),
            StudyDTO("https://kr-mb.theepochtimes.com/assets/uploads/2019/11/a58d75581e050bbc5c6acfe08ad418ff.png",
                "제목 2",
                "message2",
                "주소2",
                "010-xxxx-xxxx"
            ),
            StudyDTO("https://kr-mb.theepochtimes.com/assets/uploads/2019/11/a58d75581e050bbc5c6acfe08ad418ff.png",
                "제목 3",
                "message3",
                "주소3",
                "010-xxxx-xxxx"
            )
        ))
        Log.println(Log.DEBUG, "!!!", "!!!!")
        val binding = FragmentBottomSheetBinding.inflate(layoutInflater)
        Toast.makeText(mcontext, "!!!!!!", Toast.LENGTH_LONG).show() /////////

        binding.listView.adapter = BottomSheetListViewAdapter(mcontext, studyList)
    }

}