package com.adityabugalia.baiscloginwithrxjava.data

import android.util.Log
import com.adityabugalia.baiscloginwithrxjava.utils.RxBus

object User {

    lateinit var username: String
    lateinit var isSuccessful :String
    lateinit var errorMessage: String

    fun subscriber(rxBus: RxBus) {
        //Log.d("user-Username",""+this@User.userName)
        rxBus.send(this@User)


    }
    fun getSuccessful():Boolean{
       return isSuccessful.toBoolean()
    }

}