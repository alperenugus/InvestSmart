package theinvestors.csci448.investsmart.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import theinvestors.csci448.investsmart.data.UserModel

interface UserApi {

    @GET("/get-user/{email}/{password}")
    fun getUser(@Path("email") email: String, @Path("password") password: String): Call<Boolean>

    @POST("/add-user")
    fun saveUser(@Body newUser: UserModel): Call<Boolean>

}