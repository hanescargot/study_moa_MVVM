package com.pyrion.studymoa.model

import android.util.Log
import com.google.gson.Gson
import com.pyrion.studymoa.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudyMoaModel {


    fun getStudyListBuyAddress( addresses : ArrayList<String> ){
        // 위치 기반으로 스터디 목록 조회
        Log.d("YMC", "onResponse start: ");
        val retrofit = Retrofit.Builder()
            .baseUrl("https://aahwckksd2hbjtsfgcz2fijo6m0nxkow.lambda-url.ap-northeast-2.on.aws")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitService::class.java);
        val requestData = AddressListDTO()
//        requestData.add("경기도 화성시 병점서로 44")
        requestData.addAll(addresses)
        var dd : String = Gson().toJson(requestData)
        Log.i("!!dd", dd)
        service.requestStudyList(requestData)?.enqueue(object : Callback<ResponseStudyListDTO> {
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



    fun getMyStudyList(id : String): ArrayList<MyStudyDTO> {
        // 내가 생성한 스터디 목록 조회
        var result = ArrayList<MyStudyDTO>()
        Log.d("YMC", "onResponse start: ");
        val retrofit = Retrofit.Builder()
            .baseUrl("https://znptr463hr35bpvphj4xbqpopa0bfqtd.lambda-url.ap-northeast-2.on.aws")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitService::class.java);

        Log.i("!!requestData", id)
        service.requestMyStudyList(id)?.enqueue(object : Callback<MyStudyDTO> {
            override fun onResponse(call: Call<MyStudyDTO>, response: Response<MyStudyDTO>) {
                if(response.isSuccessful){
                    // 정상적으로 통신이 성공된 경우
                    Log.d("YMC", "onResponse 성공: " + Gson().toJson(response.body()))
                    result.add( response.body()!! )

                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("YMC", response.toString())
                    Log.d("YMC", "onResponse 실패")
                }
            }

            override fun onFailure(call: Call<MyStudyDTO>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("YMC", "onFailure 에러: " + t.message.toString());
            }

        }
        )
        return result!!
    }

    fun sendNewStudyData(studyDto : StudyDTO): String{
        // 새로운 스터디 추가
        var resultMsg = "알수없는 오류"
        val retrofit = Retrofit.Builder()
            .baseUrl("https://izgq746sfygyluwqgajenqjjku0slpkv.lambda-url.ap-northeast-2.on.aws")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitService::class.java);
        var dd : String = Gson().toJson(studyDto)
        Log.i("!!dd", dd)
        service.addNewStudy(studyDto)?.enqueue(object : Callback<ResponseAddStudyDTO> {
            override fun onResponse(call: Call<ResponseAddStudyDTO>, response: Response<ResponseAddStudyDTO>) {
                if(response.isSuccessful){
                    // 정상적으로 통신이 성공된 경우
                    Log.d("YMC", "onResponse 성공: ")
                    val res =  response.body()!!
                    if (res.getStatusCode() == 400){
                        //todo statusCode 없어서 여기로 안넘어옴
                        // 입력값 오류
                        resultMsg = res.getErrorMsg()

                    }else{
                        resultMsg = "success"
                    }



                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    //todo 대영님이 통신 실패 사인 보내면 안됨 그러면 에러 메시지도 못봄 null 처리 돼서
                    resultMsg = "통신 오류"
                }
            }

            override fun onFailure(call: Call<ResponseAddStudyDTO>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("YMC", "onFailure 에러: " + t.message.toString());
                resultMsg = t.message.toString()
            }

        }
        )
        return resultMsg

    }

}