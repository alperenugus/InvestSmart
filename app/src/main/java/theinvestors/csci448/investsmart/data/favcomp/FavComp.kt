package theinvestors.csci448.investsmart.data.favcomp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class FavComp(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var email: String,
    var company_name: String
)