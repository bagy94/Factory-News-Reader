package hr.bagy94.factorynewsreader.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log

class NetworkStatusCallback(app: Application):ConnectivityManager.NetworkCallback() {
    var isAvailable:Boolean
        private set

    init {
        val cm:ConnectivityManager = app.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val requestBuilder = NetworkRequest.Builder()
        requestBuilder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        cm.registerNetworkCallback(requestBuilder.build(),this)
        isAvailable = cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
        Log.d(NetworkStatusCallback::class.java.name, "Network state is ${isAvailable.toString()}")
    }



    override fun onAvailable(network: Network?) {
        super.onAvailable(network)
        isAvailable = true
        Log.d(NetworkStatusCallback::class.java.name, "Network available")
    }

    override fun onUnavailable() {
        super.onUnavailable()
        isAvailable = false
        Log.d(NetworkStatusCallback::class.java.name, "Network unavailable")
    }

    override fun onLost(network: Network?) {
        super.onLost(network)
        isAvailable = false
        Log.d(NetworkStatusCallback::class.java.name, "Network lost")
    }
    fun subscribe(app:Application){
        val cm:ConnectivityManager = app.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val requestBuilder = NetworkRequest.Builder()
        requestBuilder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        cm.registerNetworkCallback(requestBuilder.build(),this)
    }

}