package com.example.instagram

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstaFeedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.insta_feed_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feedListView = view.findViewById<RecyclerView>(R.id.feed_list)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        retrofitService.getInstagramPosts().enqueue(object  : Callback<ArrayList<InstaPost>>{
            override fun onResponse(
                call: Call<ArrayList<InstaPost>>,
                response: Response<ArrayList<InstaPost>>
            ) {
                val postlist = response.body()
                postlist!!.forEach{
                    Log.d("instaa", it.content)
                }
            }

            override fun onFailure(call: Call<ArrayList<InstaPost>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}