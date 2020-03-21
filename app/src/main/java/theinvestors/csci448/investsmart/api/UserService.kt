package theinvestors.csci448.investsmart.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import theinvestors.csci448.investsmart.data.UserModel
import java.util.*

private const val logTag: String = "UserServer"

class UserService {

    private var userApi: UserApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userApi = retrofit.create(UserApi::class.java)
    }

    fun getUser(email: String, password: String): LiveData<Boolean> {
        val responseLiveData: MutableLiveData<Boolean> = MutableLiveData()


        val userRequest: Call<Boolean> = userApi.getUser(email, password)

        userRequest.enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d(logTag, "There was a problem.")
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                responseLiveData.value = response.body()
                Log.d(logTag, "Got the response ${response.body().toString()}")
            }

        })

        return responseLiveData
    }

    fun saveUser(email: String, password: String): LiveData<Boolean>{

        var newUser: UserModel = UserModel()
        newUser.email = email
        newUser.password = password
        newUser.totalMoney = 10000.0
        newUser.date = Date()

        val responseLiveData: MutableLiveData<Boolean> = MutableLiveData()


        val userRequest: Call<Boolean> = userApi.saveUser(newUser)

        userRequest.enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d(logTag, "There was a problem.")
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                responseLiveData.value = response.body()
                Log.d(logTag, "Got the response ${response.body().toString()}")
            }

        })

        return responseLiveData
    }

}