package com.example.powerconsumptionmonitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

        val message = when {
            networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> {
                "Terhubung ke WiFi - Anda dapat melakukan sinkronisasi data besar."
            }
            networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> {
                "Menggunakan jaringan seluler - Matikan sinkronisasi otomatis untuk menghemat daya."
            }
            else -> {
                "Tidak ada koneksi jaringan - Hemat daya dengan mematikan fitur jaringan."
            }
        }

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
