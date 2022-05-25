package com.pyrion.studymoa.utils



import retrofit2.Call
import retrofit2.http.*

//RetorifitService.kt
interface RetrofitService {


    @POST("/")
    fun requestStudyList(@Body() places: AddressListDTO): Call<ResponseStudyListDTO>

    @GET("/")
    fun requestMyStudyList(@Query("studyId") studyId: String): Call<MyStudyDTO>

    @POST("/")
    fun addNewStudy(@Body() places: StudyDTO): Call<ResponseAddStudyDTO>


//    @GET("posts/1")
//    fun getStudent(@Query("school_id") schoolId: Int,
//                   @Query("grade") grade: Int,
//                   @Query("classroom") classroom: Int): Call<ExampleResponse>
//
//
//    //POST 예제
//    @FormUrlEncoded
//    @POST("posts")
//    fun getContactsObject(@Field("idx") idx: String): Call<JsonObject>
}