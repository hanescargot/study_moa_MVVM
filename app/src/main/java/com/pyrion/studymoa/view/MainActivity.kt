package com.pyrion.studymoa.view

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.pyrion.studymoa.R
import com.pyrion.studymoa.adapter.BottomSheetListViewAdapter
import com.pyrion.studymoa.databinding.ActivityMainBinding
import com.pyrion.studymoa.utils.StudyDTO
import com.pyrion.studymoa.view_model.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //인 액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        _binding = ActivityMainBinding.inflate(layoutInflater)
        // 인스턴스를 활용하여 생성된 뷰를 액티비티에 표시
        setContentView(_binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MapsFragment>(R.id.main_fragment)
            }
        }

        //bottomsheet custom
        var behavior = BottomSheetBehavior.from(_binding.bottomSheet)
        behavior.isFitToContents = false
        behavior.halfExpandedRatio = 0.6f


        //Bottom Sheet List View
        var listViewAdapter = BottomSheetListViewAdapter(this)
        _binding.lv.adapter = listViewAdapter
        listViewAdapter.dataList = mainViewModel.studyList
        listViewAdapter.notifyDataSetChanged()
    }
}