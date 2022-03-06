package saa.com.codetest2.datasource.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import saa.com.codetest2.model.Movie


@Database(
    entities = [Movie::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            if(context == null){
                Log.i("context " , "is null")
            }
            return Room.databaseBuilder(context, AppDatabase::class.java, "codetest2")
                .build()
        }
    }

}