package com.example.instagram

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class User(
    val token: String, val username: String, val id: Int
)

interface RetrofitService {
    @POST("user/login/")
    @FormUrlEncoded
    fun instaLogin(
            @FieldMap params: HashMap<String, Any>
    ): Call<User>

    @POST("user/signup/")
    @FormUrlEncoded
    fun instaJoin(
        @FieldMap params: HashMap<String, Any>
    ): Call<User>
}