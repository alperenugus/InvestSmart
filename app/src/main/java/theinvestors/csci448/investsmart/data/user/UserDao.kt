package theinvestors.csci448.investsmart.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE email = (:email)")
    fun getUser(email: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

}