package theinvestors.csci448.investsmart.data.favcomp

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class FavCompRepository(private val favCompDao: FavCompDao) {

    private val executor = Executors.newSingleThreadExecutor()

    companion object {
        private var instance: FavCompRepository? = null
        fun getInstance(context: Context): FavCompRepository? {
            return instance ?: let {
                if (instance == null) {
                    val database = FavCompDatabase.getInstance(context)
                    instance = FavCompRepository(database.favCompDao())
                }
                return instance
            }
        }

        fun get(): FavCompRepository {
            return instance ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }

    fun getFavComps(email: String): LiveData<FavComp?> = favCompDao.getFavComps(email)
    fun addFavComp(favComp: FavComp) = favCompDao.addFavComp(favComp)

}