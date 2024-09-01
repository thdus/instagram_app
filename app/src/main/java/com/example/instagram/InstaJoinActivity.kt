package com.example.instagram

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstaJoinActivity : AppCompatActivity() {

    var username : String = ""
    var password1 : String = ""
    var password2 : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta_join)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        findViewById<TextView>(R.id.insta_login).setOnClickListener {
            startActivity(Intent(this, InstaLoginActivity::class.java))
        }

        findViewById<EditText>(R.id.id_input).doAfterTextChanged {
            username = it.toString()
        }
        findViewById<EditText>(R.id.pw_input1).doAfterTextChanged {
            password1 = it.toString()
        }
        findViewById<EditText>(R.id.pw_input2).doAfterTextChanged {
            password2 = it.toString()
        }

        val retrofitService = retrofit.create(RetrofitService::class.java)
        val user = HashMap<String, Any>()
        findViewById<TextView>(R.id.join_btn).setOnClickListener {
            user.put("username", username)
            user.put("password1", password1)
            user.put("password2", password2)

            retrofitService.instaJoin(user).enqueue(object : Callback<UserToken>{
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
        }

    }
}