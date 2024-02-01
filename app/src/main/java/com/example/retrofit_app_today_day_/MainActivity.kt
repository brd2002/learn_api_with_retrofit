package com.example.retrofit_app_today_day_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit_app_today_day_.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val baseurl = "https://jsonplaceholder.typicode.com/"
    private val tag = "api_response"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getComment()

    }
    private fun getComment(){
        val api  = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        api.getComment().enqueue(object  : Callback<List<CommentsItem>>{
            override fun onResponse(
                call: Call<List<CommentsItem>>,
                response: Response<List<CommentsItem>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        for (comment in it) {
                            Log.i(tag , comment.email)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CommentsItem>>, t: Throwable) {
                Log.i(tag, t.message.toString())
            }

        })
    }
}