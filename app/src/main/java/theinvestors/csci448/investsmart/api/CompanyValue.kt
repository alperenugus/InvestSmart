package theinvestors.csci448.investsmart.api

import com.google.gson.annotations.SerializedName

data class CompanyValue(
    @SerializedName("o") val open : String,
    @SerializedName("h") val high : String,
    @SerializedName("l") val low : String,
    @SerializedName("c") val current : String,
    @SerializedName("pc") val pc: String,
    @SerializedName("t") val time: String
)