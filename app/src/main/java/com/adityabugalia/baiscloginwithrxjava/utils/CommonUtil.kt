package com.adityabugalia.baiscloginwithrxjava.utils


import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.adityabugalia.baiscloginwithrxjava.R

object CommonUtil {

    fun isInternetAvailable(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            if (netInfo != null && netInfo.isConnectedOrConnecting) {
                return true
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.toast_no_internet),
                    Toast.LENGTH_SHORT
                ).show()
            }
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}