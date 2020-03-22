package theinvestors.csci448.investsmart.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import theinvestors.csci448.investsmart.data.AssetModel

interface AssetApi {

    @GET("/get-assets/{email}")
    fun getAssets(@Path("email") email: String): Call<List<AssetModel>>

    @POST("/add-asset")
    fun saveAsset(@Body newAsset: AssetModel): Call<Boolean>
}