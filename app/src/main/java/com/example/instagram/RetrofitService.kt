package com.example.instagram

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.io.File

class User(
    val token: String, val username: String, val id: Int
)

class InstaPost(
    val id: Int, val content: String, val image: String, val owner_profile: OwnerProfile
)

class OwnerProfile(
    val username: String, val image: String?
)



interface RetrofitService {

    @POST("instagram/post/like/{post_id}/")
    fun postLike(
        @Path("post_id") post_id: Int
    ): Call<Any>

    @GET("instagram/post/list/all/")
    fun getInstagramPosts(
    ):Call<ArrayList<InstaPost>>

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