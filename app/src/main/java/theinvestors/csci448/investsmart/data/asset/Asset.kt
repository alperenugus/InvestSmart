package theinvestors.csci448.investsmart.data.asset

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Asset(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var email: String,
    var company: String,
    var owned_shares: Int
)