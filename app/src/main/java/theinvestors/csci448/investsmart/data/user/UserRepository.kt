package theinvestors.csci448.investsmart.data.user

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class UserRepository(private val userDao: UserDao) {

    private val executor = Executors.newSingleThreadExecutor()

    companion object {
        private var instance: UserRepository? = null
        fun getInstance(context: Context): UserRepository? {
            return instance ?: let {
                if (instance == null) {
                    val database = UserDatabase.getInstance(context)
                    instance = UserRepository(database.userDao())
                }
                return instance
            }
        }

        fun get(): UserRepository {
            return instance ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }

    fun getUser(email: String): LiveData<User?> = userDao.getUser(email)

    fun addUser(user: User){
        executor.execute{
            userDao.addUser(user)
        }
    }

    fun deleteUser(email: String){
        executor.execute{
            userDao.deleteUser(email)
        }
    }

}