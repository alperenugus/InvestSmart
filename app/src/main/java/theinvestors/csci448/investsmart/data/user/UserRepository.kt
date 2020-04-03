package theinvestors.csci448.investsmart.data.user

import android.content.Context
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

    fun getUser(email: String): User? = userDao.getUser(email)
    fun addUser(user: User) = userDao.addUser(user)

}