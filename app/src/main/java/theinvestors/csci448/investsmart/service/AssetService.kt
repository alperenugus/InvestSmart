package theinvestors.csci448.investsmart.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import theinvestors.csci448.investsmart.api.AssetApi
import theinvestors.csci448.investsmart.data.AssetModel
import kotlin.math.log

private const val logTag: String = "AssetServer"

class AssetService {

    private var assetApi: AssetApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        assetApi = retrofit.create(AssetApi::class.java)
    }


    fun getAssets(email: String): MutableLiveData<List<AssetModel>> {
        val responseLiveData: MutableLiveData<List<AssetModel>> = MutableLiveData()


        val assetRequest: Call<List<AssetModel>> = assetApi.getAssets(email)

        assetRequest.enqueue(object : Callback<List<AssetModel>> {
            override fun onFailure(call: Call<List<AssetModel>>, t: Throwable) {
                Log.d(logTag, "There was a problem.")
                Log.d(logTag, t.toString())
            }

            override fun onResponse(
                call: Call<List<AssetModel>>,
                response: Response<List<AssetModel>>
            ) {
                responseLiveData.value = response.body()
                Log.d(logTag, "Got the response ${response.body().toString()}")
            }
        })

        return responseLiveData
    }


    // TO DO: Think about if a user has shares from a company and wants to add more.
    fun saveAsset(){

    }

}