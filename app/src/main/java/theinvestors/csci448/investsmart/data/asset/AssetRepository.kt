package theinvestors.csci448.investsmart.data.asset

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class AssetRepository(private val assetDao: AssetDao) {

    private val executor = Executors.newSingleThreadExecutor()

    companion object {
        private var instance: AssetRepository? = null
        fun getInstance(context: Context): AssetRepository? {
            return instance ?: let {
                if (instance == null) {
                    val database = AssetDatabase.getInstance(context)
                    instance = AssetRepository(database.assetDao())
                }
                return instance
            }
        }

        fun get(): AssetRepository {
            return instance ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }

    fun getAssets(email: String): LiveData<Asset?> = assetDao.getAssets(email)
    fun addAsset(asset: Asset) = assetDao.addAsset(asset)

}