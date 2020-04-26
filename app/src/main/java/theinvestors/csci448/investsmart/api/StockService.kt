package theinvestors.csci448.investsmart.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.nield.kotlinstatistics.simpleRegression
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

private const val logTag: String = "StockService"
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

    fun getHistoricValue(symbol: String): LiveData<CompanyHistoricValue>{

        val responseLiveData: MutableLiveData<CompanyHistoricValue> = MutableLiveData()

        var dateNow = Calendar.getInstance().timeInMillis
        var datePast = getDaysAgo(7).timeInMillis

        // Miliseconds to seconds
        dateNow /= 1000
        datePast /= 1000

        Log.d(logTag, dateNow.toString())
        Log.d(logTag, datePast.toString())

        val request : Call<CompanyHistoricValue> = stockApi.getCompanyHistoricValue(symbol, "D", datePast.toString(), dateNow.toString(), TOKEN)

        request.enqueue(object : Callback<CompanyHistoricValue>{
            override fun onFailure(call: Call<CompanyHistoricValue>, t: Throwable) {
                Log.d(logTag, "There was a problem.")
                Log.d(logTag, t.message.toString())
            }

            override fun onResponse(call: Call<CompanyHistoricValue>, response: Response<CompanyHistoricValue>) {
                responseLiveData.value = response.body()
                Log.d(logTag, "Got the response ${response.body().toString()}")
                Log.d(logTag, response.code().toString())
            }

        })
        return responseLiveData
    }

    private fun getDaysAgo(daysAgo: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

        return calendar
    }

}