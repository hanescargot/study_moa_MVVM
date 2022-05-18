package com.pyrion.studymoa.view_model
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.pyrion.studymoa.utils.RequestStudyDTO
import com.pyrion.studymoa.utils.ResponseStudyListDTO
import com.pyrion.studymoa.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val _studyList = MutableLiveData<ArrayList<StudyDTO>>()
    val studyList : LiveData<ArrayList<StudyDTO>> get() = _studyList

    private val _myStudyList = MutableLiveData<ArrayList<StudyDTO>>()
    val myStudyList : LiveData<ArrayList<StudyDTO>> get() = _myStudyList

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



        _myStudyList.value = arrayListOf(
            StudyDTO("https://kr-mb.theepochtimes.com/assets/uploads/2019/11/a58d75581e050bbc5c6acfe08ad418ff.png",
                "제목 4",
                "message4",
                "주소4",
                "010-xxxx-xxxx"
            ),
            StudyDTO("https://kr-mb.theepochtimes.com/assets/uploads/2019/11/a58d75581e050bbc5c6acfe08ad418ff.png",
                "제목 5",
                "message5",
                "주소5",
                "010-xxxx-xxxx"
            ),
            StudyDTO("https://kr-mb.theepochtimes.com/assets/uploads/2019/11/a58d75581e050bbc5c6acfe08ad418ff.png",
                "제목 6",
                "message6",
                "주소6",
                "010-xxxx-xxxx"
            )
        )
    }

    fun setStudyList(newStudyList : ArrayList<StudyDTO>){
        _studyList.value = newStudyList
    }
    fun setMyStudyList(newMyStudyList : ArrayList<StudyDTO>){
        _myStudyList.value = newMyStudyList
    }

    fun loadStudyList(){
        Log.d("YMC", "onResponse start: ");
        val retrofit = Retrofit.Builder()
            .baseUrl("https://aahwckksd2hbjtsfgcz2fijo6m0nxkow.lambda-url.ap-northeast-2.on.aws")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitService::class.java);
        val requestData = RequestStudyDTO()
        requestData.add("경기도 화성시 병점서로 44")
        var dd : String = Gson().toJson(requestData)
        Log.i("!!dd", dd)
        service.getStudyList(requestData)?.enqueue(object : Callback<ResponseStudyListDTO>{
            override fun onResponse(
                call: Call<ResponseStudyListDTO>,
                response: Response<ResponseStudyListDTO>
            ) {
                if(response.isSuccessful){
                    // 정상적으로 통신이 성공된 경우
//                    var result: ArrayList<StudyDTO>? = response.body()
                    Log.d("YMC", "onResponse 성공: " + Gson().toJson(response.body()))

                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("YMC", response.toString())
                    Log.d("YMC", "onResponse 실패")
                }
            }

            override fun onFailure(call: Call<ResponseStudyListDTO>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("YMC", "onFailure 에러: " + t.message.toString());
            }

        })
    }
    fun loadMyStudyList(){

    }


}


