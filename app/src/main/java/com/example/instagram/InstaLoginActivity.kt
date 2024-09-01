package com.example.instagram

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstaLoginActivity : AppCompatActivity() {
    var username : String = ""
    var password : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta_login)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        findViewById<EditText>(R.id.id_input).doAfterTextChanged {
            username = it.toString()
        }
        findViewById<EditText>(R.id.id_input).doAfterTextChanged {
            password = it.toString()
        }
        findViewById<TextView>(R.id.insta_join).setOnClickListener {
            startActivity(Intent(this, InstaJoinActivity::class.java))
        }

        findViewById<TextView>(R.id.login_btn).setOnClickListener {
            val user = HashMap<String, Any>()
            user.put("username", username)
            user.put("password", password)
            retrofitService.instaLogin(user).enqueue(object: Callback<UserToken> {
                override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                    if(response.isSuccessful){
                        val userToken = response.body()!!
                        val sharedPreferences =
                            getSharedPreferences("user_info", Context.MODE_PRIVATE)
                        val editor : SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("token", userToken.token)
                        editor.commit()
                    }
                }

                override fun onFailure(call: Call<UserToken>, t: Throwable) {
                }
            })
    }}
}