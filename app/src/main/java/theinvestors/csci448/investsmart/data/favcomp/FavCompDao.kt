package theinvestors.csci448.investsmart.data.favcomp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavCompDao {

    @Query("SELECT * FROM favcomp WHERE email = (:email)")
    fun getFavComps(email: String): LiveData<List<FavComp>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavComp(favComp: FavComp)

}