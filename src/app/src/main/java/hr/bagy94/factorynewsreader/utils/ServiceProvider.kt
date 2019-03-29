package hr.bagy94.factorynewsreader.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

object ServiceProvider {
    private val baseUrl = "https://newsapi.org"
    private val retrofit: Retrofit by lazy {
        val builder = Retrofit.Builder()
        builder.baseUrl(baseUrl)
        builder.client(OkHttpClient())
        builder.addConverterFactory(GsonConverterFactory.create())
        builder.build()
    }

    fun<T> getService(kClass: Class<T>):T = retrofit.create(kClass)
}
