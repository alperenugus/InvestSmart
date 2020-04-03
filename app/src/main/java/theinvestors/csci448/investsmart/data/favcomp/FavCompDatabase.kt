package theinvestors.csci448.investsmart.data.favcomp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


private const val DATABASE_NAME = "favcomp-database"

@Database(entities = [FavComp::class], version = 1)
@TypeConverters(FavCompTypeConverters::class)
abstract class FavCompDatabase: RoomDatabase() {

    companion object {
        private var instance: FavCompDatabase? = null

        fun getInstance(context: Context): FavCompDatabase {
            return instance ?: let {
                instance ?: Room.databaseBuilder(
                    context,
                    FavCompDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
        }
    }

    abstract fun favCompDao(): FavCompDao

}