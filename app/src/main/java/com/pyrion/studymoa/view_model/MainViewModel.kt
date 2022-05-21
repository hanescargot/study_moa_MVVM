package com.pyrion.studymoa.view_model
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pyrion.studymoa.model.StudyMoaModel
import com.pyrion.studymoa.utils.MyStudyDTO
import com.pyrion.studymoa.utils.StudyDTO

class MainViewModel : ViewModel() {

    private val _studyList = MutableLiveData<ArrayList<StudyDTO>>()
    val studyList : LiveData<ArrayList<StudyDTO>> get() = _studyList

    private val _myStudyList = MutableLiveData<ArrayList<MyStudyDTO>>()
    val myStudyList : LiveData<ArrayList<MyStudyDTO>> get() = _myStudyList

    private val model = StudyMoaModel()

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

//        _myStudyList.value = arrayListOf(
//            StudyDTO("https://kr-mb.theepochtimes.com/assets/uploads/2019/11/a58d75581e050bbc5c6acfe08ad418ff.png",
//                "제목 4",
//                "message4",
//                "주소4",
//                "010-xxxx-xxxx"
//            ),
//            StudyDTO("https://kr-mb.theepochtimes.com/assets/uploads/2019/11/a58d75581e050bbc5c6acfe08ad418ff.png",
//                "제목 5",
//                "message5",
//                "주소5",
//                "010-xxxx-xxxx"
//            ),
//            StudyDTO("https://kr-mb.theepochtimes.com/assets/uploads/2019/11/a58d75581e050bbc5c6acfe08ad418ff.png",
//                "제목 6",
//                "message6",
//                "주소6",
//                "010-xxxx-xxxx"
//            )
//        )
        _myStudyList.value =  model.getMyStudyList("2")
    }

    fun setStudyList(newStudyList : ArrayList<StudyDTO>){
        _studyList.value = newStudyList
    }
    fun setMyStudyList(newMyStudyList : ArrayList<MyStudyDTO>){
        _myStudyList.value = newMyStudyList
    }

    fun onClickMyBtn(){
        // 자동으로 업데이트 되는지 확인 한 뒤에 사용해 보기
//        model.getMyStudyList("2")
    }

    fun onClickSubmitBtn(studyDto : StudyDTO): String{
        return  model.sendNewStudyData(studyDto)
    }



}


