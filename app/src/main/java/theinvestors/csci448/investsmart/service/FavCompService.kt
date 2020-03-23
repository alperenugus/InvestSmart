package theinvestors.csci448.investsmart.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import theinvestors.csci448.investsmart.api.FavCompApi
import theinvestors.csci448.investsmart.data.AssetModel
import theinvestors.csci448.investsmart.data.FavCompModel

private const val logTag: String = "FavCompService"

class FavCompService {

    private var favCompApi: FavCompApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        favCompApi = retrofit.create(FavCompApi::class.java)
    }

    fun getFavComps(email: String): MutableLiveData<List<FavCompModel>> {
        val responseLiveData: MutableLiveData<List<FavCompModel>> = MutableLiveData()


        val assetRequest: Call<List<FavCompModel>> = favCompApi.getFavComp(email)

        assetRequest.enqueue(object : Callback<List<FavCompModel>> {
            override fun onFailure(call: Call<List<FavCompModel>>, t: Throwable) {
                Log.d(logTag, "There was a problem.")
                Log.d(logTag, t.toString())
            }

            override fun onResponse(
                call: Call<List<FavCompModel>>,
                response: Response<List<FavCompModel>>
            ) {
                responseLiveData.value = response.body()
                Log.d(logTag, "Got the response ${response.body().toString()}")
            }
        })

        return responseLiveData
    }

}