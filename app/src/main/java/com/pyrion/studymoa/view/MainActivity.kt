package com.pyrion.studymoa.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.pyrion.studymoa.R
import com.pyrion.studymoa.adapter.BottomSheetRecyclerViewAdapter
import com.pyrion.studymoa.databinding.ActivityMainBinding
import com.pyrion.studymoa.utils.StudyDTO
import com.pyrion.studymoa.view_model.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //인 액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        _binding = ActivityMainBinding.inflate(layoutInflater)
        // 인스턴스를 활용하여 생성된 뷰를 액티비티에 표시
        setContentView(binding.root)

        //지도 Fragment 표시
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MapsFragment>(R.id.main_fragment)
            }
        }

        //bottomsheet custom
        var behavior = BottomSheetBehavior.from(binding.bottomSheet)
        behavior.isFitToContents = false
        behavior.halfExpandedRatio = 0.6f

        //Bottom Sheet List View
        Log.i("!!","start")
        mainViewModel.studyList.value?.get(0)?.let { Log.i("!!", it.title) }
        binding.lv.layoutManager = LinearLayoutManager(this)
        val adapter = BottomSheetRecyclerViewAdapter(this, mainViewModel.studyList)
        binding.lv.adapter = adapter
        val dataObserver: Observer<ArrayList<StudyDTO>> = Observer {
            Log.i("!!","update")
            val recyclerViewItems = MutableLiveData<ArrayList<StudyDTO>>()
            recyclerViewItems.value = it
            val adapter = BottomSheetRecyclerViewAdapter(this, recyclerViewItems)
            binding.lv.adapter = adapter
        }
        mainViewModel.studyList.observe(this, dataObserver)
    }
}