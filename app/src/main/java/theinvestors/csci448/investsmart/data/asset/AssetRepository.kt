package theinvestors.csci448.investsmart.data.asset

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

private const val logTag: String = "AssetRepository"

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

    fun getAsset(email: String, company: String): LiveData<Asset>{
        return assetDao.getAsset(email, company)
    }


    fun getAssets(email: String): LiveData<List<Asset>> {
            return assetDao.getAssets(email)
    }

    fun addAsset(asset: Asset){
        executor.execute {
            assetDao.addAsset(asset)
            Log.d(logTag, "Added ${asset.toString()}")
        }
    }

    fun deleteAll(){
        executor.execute{
            assetDao.deleteAll()
            Log.d(logTag, "Database deleted")
        }
    }

}