package com.example.rickandmortyretrofitnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rickandmortyretrofitnew.network.ApiClient
import com.example.rickandmortyretrofitnew.network.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = ApiClient.apiService.fetchCharacters("1")

        client.enqueue(object : Callback<CharacterResponse> {

            // on successful response we log the result
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("Characters", "" + response.body())
                }
            }

            // on failure we log the error message
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("Fail", "" + t.message)
            }
        })
    }
}