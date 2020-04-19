package theinvestors.csci448.investsmart.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val logTag: String = "AlphaService"
private const val TOKEN: String = "bqdtalvrh5rfb8as3mc0"

class StockService{

    private var stockApi: StockApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://finnhub.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        stockApi = retrofit.create(StockApi::class.java)
    }

    fun getValue(symbol: String): LiveData<CompanyValue> {

        val responseLiveData: MutableLiveData<CompanyValue> = MutableLiveData()

        val request : Call<CompanyValue> = stockApi.getCompanyValue(symbol, TOKEN)

        request.enqueue(object : Callback<CompanyValue>{
            override fun onFailure(call: Call<CompanyValue>, t: Throwable) {
                Log.d(logTag, "There was a problem.")
                Log.d(logTag, t.message.toString())
            }

            override fun onResponse(call: Call<CompanyValue>, response: Response<CompanyValue>) {
                responseLiveData.value = response.body()
                Log.d(logTag, "Got the response ${response.body().toString()}")
                Log.d(logTag, response.code().toString())
            }

        })
        return responseLiveData
    }

}