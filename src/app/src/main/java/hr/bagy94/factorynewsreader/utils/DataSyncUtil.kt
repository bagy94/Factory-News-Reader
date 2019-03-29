package hr.bagy94.factorynewsreader.utils

import android.content.Context
import android.content.SharedPreferences


class DataSyncUtil(context: Context) {

    private val sharedPreferences:SharedPreferences = context.getSharedPreferences(LAST_SYNCED_SP,Context.MODE_PRIVATE)

    fun onDataSynced(syncKey:String){
        sharedPreferences.edit().apply {
            putLong(syncKey,System.currentTimeMillis())
        }.apply()
    }

    fun getLastSynceded(syncedKey:String):Long{
        return sharedPreferences.getLong(syncedKey,0)
    }

    companion object {
        private const val LAST_SYNCED_SP = "hr.bagy94.factorynewsreader.SYNC_SP"
    }
}