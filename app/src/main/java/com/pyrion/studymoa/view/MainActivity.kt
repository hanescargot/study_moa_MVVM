package com.pyrion.studymoa.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.pyrion.studymoa.R
import com.pyrion.studymoa.adapter.BottomSheetRecyclerViewAdapter
import com.pyrion.studymoa.adapter.BottomSheetRecyclerViewAdapter.OnRecyclerItemClickListener
import com.pyrion.studymoa.databinding.ActivityMainBinding
import com.pyrion.studymoa.databinding.DialogAddStudyBinding
import com.pyrion.studymoa.databinding.DialogDetailStudyBinding
import com.pyrion.studymoa.utils.StudyDTO
import com.pyrion.studymoa.view_model.MainViewModel

class MainActivity : AppCompatActivity(){

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
        //나의 스터디 목록 보기 버튼
        binding.btn.setOnClickListener{
            showMyStudyListDialog()
        }


        //스터디 추가 버튼
        binding.btn.setOnClickListener(View.OnClickListener {
            showAddStudyDialog()
        })

        //bottomsheet custom
        val behavior = BottomSheetBehavior.from(binding.bottomSheet)
        behavior.isFitToContents = false
        behavior.halfExpandedRatio = 0.6f

        //Bottom Sheet List View
        mainViewModel.studyList.value?.get(0)?.let { Log.i("!!", it.title) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = BottomSheetRecyclerViewAdapter(mainViewModel.studyList)

        adapter.setOnItemClickListener(object : OnRecyclerItemClickListener {
            override fun onRecyclerItemClick(studyDto: StudyDTO) {
                // 리사이클러 뷰 클릭 리스너 인터페이스 구현
                //상세정보 얼럿
                showStudyDetailDialog(studyDto)
            }
        })
        binding.recyclerView.adapter = adapter
        val dataObserver: Observer<ArrayList<StudyDTO>> = Observer {
            adapter.setItems(it)
            adapter.notifyDataSetChanged()
        }
        mainViewModel.studyList.observe(this, dataObserver)
    }

    private fun showMyStudyListDialog() {
        dialog = Dialog(this)
        dialog.setTitle("게시한 나의 스터디 목록")
        dialog.setContentView(R.layout.dialog_my_study)
        dialog.show()
    }


    lateinit var dialog  : Dialog
    fun showStudyDetailDialog(studyDto: StudyDTO) {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)// 타이틀 제거
        val dialogBinding = DialogDetailStudyBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        Glide.with(this).load(studyDto.imgUrl).into(dialogBinding.iv);
        dialogBinding.tvTitle.text = studyDto.title
        dialogBinding.tvAddress.text = studyDto.address
        dialogBinding.tvPhoneNumber.text = studyDto.phoneNumber
        dialogBinding.tvDescription.text = studyDto.description
        dialog.show()
    }


    private fun showAddStudyDialog() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)// 타이틀 제거
        val dialogBinding = DialogAddStudyBinding.inflate(layoutInflater)
        dialogBinding.btn.setOnClickListener {
            //todo submit
            Toast.makeText(this, "등록 완료", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialog.setContentView(dialogBinding.root)
        dialog.show()
    }

}