package com.pyrion.studymoa.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ListView
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
import com.pyrion.studymoa.adapter.MyStudyListViewAdapter
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
        _binding.myBtn.setOnClickListener{
            mainViewModel.loadStudyList()

            showMyStudyListDialog()
        }


        //스터디 추가 버튼
        _binding.btn.setOnClickListener(View.OnClickListener {
            showAddStudyDialog()
        })

        //bottomsheet custom
        val behavior = BottomSheetBehavior.from(_binding.bottomSheet)
        behavior.isFitToContents = false
        behavior.halfExpandedRatio = 0.6f

        //Bottom Sheet List View
        mainViewModel.studyList.value?.get(0)?.let { Log.i("!!", it.title) }
        _binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = BottomSheetRecyclerViewAdapter(mainViewModel.studyList)

        adapter.setOnItemClickListener(object : OnRecyclerItemClickListener {
            override fun onRecyclerItemClick(studyDto: StudyDTO) {
                // 리사이클러 뷰 클릭 리스너 인터페이스 구현
                //상세정보 얼럿
                showStudyDetailDialog(studyDto)
            }
        })
        _binding.recyclerView.adapter = adapter
        val dataObserver: Observer<ArrayList<StudyDTO>> = Observer {
            adapter.setItems(it)
            adapter.notifyDataSetChanged()
        }
        mainViewModel.studyList.observe(this, dataObserver)
    }

    lateinit var myStudyDialog  : Dialog
    private fun showMyStudyListDialog() {
        //최초로 화면을 로딩한 후에도 스크롤을 움직이는 등 액션을 취하면 그 때마다 findViewById를 통해 convertView에 들어갈 요소를 찾는다.
        // 스크롤 할 때마다 View를 찾으면 리소스를 많이 사용하게 되고, 속도가 느려진다.
        myStudyDialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_my_study, null)
        val listView = view.findViewById<ListView>(R.id.list_view)
        val adapter = MyStudyListViewAdapter(this, mainViewModel.myStudyList)//todo 데이터 바꿔야 함
        adapter.setOnEditButtonClickListener(object :
            MyStudyListViewAdapter.OnEditButtonClickListener{
                override fun onClickEdit(context: Context, studyDto: StudyDTO) {
                    //스터디 수정 버튼 클릭
                    showEditStudyDialog(studyDto)

                }
            }
        )

        adapter.setOnDeleteButtonClickListener(object :
            MyStudyListViewAdapter.OnDeleteButtonClickListener {
            override fun onClickDelete(context: Context) {
                super.onClickDelete(context)
                var builder = AlertDialog.Builder(context)
                builder.setTitle("나의 스터디 삭제")
                    .setMessage("선택한 스터디를 삭제하시겠습니까? 삭제된 스터디는 복구되지 않습니다.")
                    .setPositiveButton("삭제",
                        DialogInterface.OnClickListener { _, _ ->
                            Toast.makeText(context, "삭제 완료", Toast.LENGTH_SHORT).show()
                        })
                    .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    })
                builder.show()
            }
        })
        listView.adapter = adapter
        myStudyDialog.setContentView(view)
        myStudyDialog.show()

    }


    lateinit var studyDetailDialog  : Dialog
    fun showStudyDetailDialog(studyDto: StudyDTO) {
        studyDetailDialog = Dialog(this)
        studyDetailDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)// 타이틀 제거
        val dialogBinding = DialogDetailStudyBinding.inflate(layoutInflater)
        studyDetailDialog.setContentView(dialogBinding.root)
        Glide.with(this).load(studyDto.imgUrl).into(dialogBinding.iv);
        dialogBinding.tvTitle.text = studyDto.title
        dialogBinding.tvAddress.text = studyDto.address
        dialogBinding.tvPhoneNumber.text = studyDto.contact
        dialogBinding.tvDescription.text = studyDto.description
        studyDetailDialog.show()
    }

    lateinit var addStudyDialog  : Dialog
    private fun showAddStudyDialog() {
        addStudyDialog = Dialog(this)
        addStudyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)// 타이틀 제거
        val dialogBinding = DialogAddStudyBinding.inflate(layoutInflater)
        dialogBinding.btn.setOnClickListener {
            //todo submit
            Toast.makeText(this, "등록 완료", Toast.LENGTH_SHORT).show()
            addStudyDialog.dismiss()
        }
        addStudyDialog.setContentView(dialogBinding.root)
        addStudyDialog.show()
    }

    lateinit var editStudyDialog  : Dialog
    private fun showEditStudyDialog(studyDto : StudyDTO) {
        editStudyDialog = Dialog(this)
        editStudyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)// 타이틀 제거
        val dialogBinding = DialogAddStudyBinding.inflate(layoutInflater)
        //입력난에 수정 전 내용 입력
        dialogBinding.etTitle.setText(studyDto.title)
        dialogBinding.etAddress.setText(studyDto.address)
        dialogBinding.etPhoneNumber.setText(studyDto.contact)
        dialogBinding.etDescription.setText(studyDto.description)
        dialogBinding.btn.text="수정"
        dialogBinding.btn.setOnClickListener {
            //todo submit
            Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show()
            editStudyDialog.dismiss()
        }
        editStudyDialog.setContentView(dialogBinding.root)
        editStudyDialog.show()
    }

}