package com.pyrion.studymoa.view_model
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pyrion.studymoa.utils.StudyDTO
class MainViewModel : ViewModel() {

    private val _studyList = MutableLiveData<ArrayList<StudyDTO>>()
    val studyList : LiveData<ArrayList<StudyDTO>> get() = _studyList

    init {
        _studyList.value = arrayListOf(
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
        )
    }

    fun setStudyList(newStudyList : ArrayList<StudyDTO>){
        _studyList.value = newStudyList
    }

}


