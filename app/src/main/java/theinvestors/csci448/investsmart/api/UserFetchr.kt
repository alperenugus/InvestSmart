package theinvestors.csci448.investsmart.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val logTag: String = "UserFetchr"

class UserFetchr {

    private var userApi: UserApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        userApi = retrofit.create(UserApi::class.java)
    }

    fun getUser(): LiveData<String> {
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val userRequest: Call<String> = userApi.getHello()

        userRequest.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(logTag, t.toString())
                Log.d(logTag, "There was a problem.")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(logTag, "Response received: ${response.body()}")
                responseLiveData.value = response.body()
            }

        })

        return responseLiveData
    }

}