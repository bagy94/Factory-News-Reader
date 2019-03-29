package hr.bagy94.factorynewsreader.repository

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile private var mDatabase:NewsLocalDatabase? = null

    @Synchronized
    fun getDatabase(context: Context):NewsLocalDatabase{
        if(mDatabase == null){
            mDatabase = Room.databaseBuilder(context,NewsLocalDatabase::class.java,"news_factory.db").build()
        }
        return mDatabase!!
    }
}