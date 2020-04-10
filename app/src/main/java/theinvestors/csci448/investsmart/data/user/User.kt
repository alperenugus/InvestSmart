package theinvestors.csci448.investsmart.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var email:String,
    var totalmoney: Double
)