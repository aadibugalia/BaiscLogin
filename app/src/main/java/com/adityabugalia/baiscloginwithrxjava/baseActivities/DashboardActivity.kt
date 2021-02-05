package com.adityabugalia.baiscloginwithrxjava.baseActivities

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.adityabugalia.baiscloginwithrxjava.R
import com.adityabugalia.baiscloginwithrxjava.data.User
import com.adityabugalia.baiscloginwithrxjava.utils.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    var rxBus = RxBus()
    val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRxBusListener()
        setContentView(R.layout.activity_dashboard)
    }

    override fun onResume() {
        super.onResume()
        User.subscriber(rxBus)
    }

    // this will recieve userObject from User class
    private fun initRxBusListener() {
        compositeDisposable.add(
            rxBus
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { receiverObject ->
                    if (receiverObject != null) {
                        if (receiverObject is User) {
                            // Receive data once it retrive from API
                            userNameTextView.text = receiverObject.username
                            Log.d("userResponse", "" + receiverObject)
                        }
                    }
                })
        )
    }

}