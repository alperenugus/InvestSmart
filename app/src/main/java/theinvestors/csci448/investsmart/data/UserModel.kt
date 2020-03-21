package theinvestors.csci448.investsmart.data

import java.util.*

data class UserModel(val id: UUID = UUID.randomUUID(),
                     var email: String? = null,
                     var password: String? = null,
                     var totalMoney: Double = 0.0,
                     val date: Date = Date()
)