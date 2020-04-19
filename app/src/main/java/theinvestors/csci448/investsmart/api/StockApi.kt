package theinvestors.csci448.investsmart.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET("quote?")
    fun getCompanyValue(
        @Query("symbol") symbol: String,
        @Query("token") token: String
    ): Call<CompanyValue>
}