package com.adityabugalia.baiscloginwithrxjava.data


import com.adityabugalia.baiscloginwithrxjava.utils.RxBus

object ApiCalls {

    //this mocks the api call and returns fixed response.
    private val TAG = "ApiCalls"

    fun performLogin(rxBus: RxBus) {
        var getURL = ApiData.API_URL
        val requestParam = null
        val success = "{isSuccessful: true, "+ User.username +": String}"
        rxBus.send(success)


    }
}