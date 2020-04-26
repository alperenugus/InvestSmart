package theinvestors.csci448.investsmart.api

import com.google.gson.annotations.SerializedName

data class CompanyHistoricValue(
    @SerializedName("c") val current : List<String>,
    @SerializedName("t") val timestamp : List<String>
)