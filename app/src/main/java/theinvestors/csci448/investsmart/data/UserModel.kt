package theinvestors.csci448.investsmart.data

import java.util.*

data class UserModel(
    var id: UUID = UUID.randomUUID(),
    var email: String? = null,
    var password: String? = null,
    var totalMoney: Double = 0.0,
    var date: Date = Date()
)