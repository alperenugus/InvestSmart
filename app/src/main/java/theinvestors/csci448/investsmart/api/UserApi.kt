package theinvestors.csci448.investsmart.api

import retrofit2.Call
import retrofit2.http.GET

interface UserApi {

    @GET("/")
    fun getHello(): Call<String>

}