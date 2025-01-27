package theinvestors.csci448.investsmart.data.asset

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AssetDao {

    @Query("SELECT * FROM asset WHERE email = (:email) AND company = (:company)")
    fun getAsset(email: String, company: String): LiveData<Asset>


    @Query("SELECT * FROM asset WHERE email = (:email)")
    fun getAssets(email: String): LiveData<List<Asset>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAsset(asset: Asset)

    @Delete
    fun deleteAsset(asset: Asset)

    @Query("DELETE FROM asset")
    fun deleteAll()
}