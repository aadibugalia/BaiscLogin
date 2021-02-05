package com.adityabugalia.baiscloginwithrxjava.baseActivities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.adityabugalia.baiscloginwithrxjava.utils.RxBus

abstract class AbstractHeaderActivity<T : ViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: T
    protected var isInternetAvailable = false
    lateinit var rxBus: RxBus
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
        rxBus = RxBus()
        checkInternetConnection()
    }

    abstract fun getChildContentView(rootView: ViewGroup): View?
    abstract fun initViewModel()

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkInternetConnection() {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetwork
        isInternetAvailable = network != null
    }
}