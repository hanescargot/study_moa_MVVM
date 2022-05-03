package com.pyrion.studymoa.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.pyrion.studymoa.R
import com.pyrion.studymoa.adapter.BottomSheetListViewAdapter
import com.pyrion.studymoa.databinding.ActivityMainBinding
import com.pyrion.studymoa.utils.StudyDTO

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //인 액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 인스턴스를 활용하여 생성된 뷰를 액티비티에 표시
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MapsFragment>(R.id.main_fragment)
            }
        }

        //todo Model에서 Call
        val datas = mutableListOf<StudyDTO>()
        datas.apply {
            addAll(listOf(
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
        }
        //Bottom Sheet List View
        var listViewAdapter = BottomSheetListViewAdapter(this)
        binding.lv.adapter = listViewAdapter
        listViewAdapter.dataList = datas
        listViewAdapter.notifyDataSetChanged()
    }
}