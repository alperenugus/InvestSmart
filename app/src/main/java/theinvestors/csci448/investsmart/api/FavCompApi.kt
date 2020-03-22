package theinvestors.csci448.investsmart.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import theinvestors.csci448.investsmart.data.FavCompModel

interface FavCompApi {

    @GET("/get-fav-comp/{email}")
    fun getFavComp(@Path("email") email: String): Call<List<FavCompModel>>

    @POST("/add-fav-comp")
    fun saveFavComp(@Body newFavComp: FavCompModel): Call<Boolean>

}