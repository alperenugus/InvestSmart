package theinvestors.csci448.investsmart.data.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

private const val DATABASE_NAME = "user-database"

@Database(entities = [User::class], version = 1)
@TypeConverters(UserTypeConverters::class)
abstract class UserDatabase: RoomDatabase() {

    companion object {
        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            return instance ?: let {
                instance ?: Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
        }
    }

    abstract fun userDao(): UserDao

}