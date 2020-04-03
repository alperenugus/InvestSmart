package theinvestors.csci448.investsmart.data.asset

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

private const val DATABASE_NAME = "asset-database"

@Database(entities = [Asset::class], version = 1)
@TypeConverters(AssetTypeConverters::class)

abstract class AssetDatabase: RoomDatabase() {

    companion object {
        private var instance: AssetDatabase? = null

        fun getInstance(context: Context): AssetDatabase {
            return instance ?: let {
                instance ?: Room.databaseBuilder(
                    context,
                    AssetDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
        }
    }

    abstract fun assetDao(): AssetDao

}