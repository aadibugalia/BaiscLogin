package com.adityabugalia.baiscloginwithrxjava.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adityabugalia.baiscloginwithrxjava.data.ApiCalls
import com.adityabugalia.baiscloginwithrxjava.data.InputModel
import com.adityabugalia.baiscloginwithrxjava.data.User
import com.adityabugalia.baiscloginwithrxjava.utils.Constants
import com.adityabugalia.baiscloginwithrxjava.utils.RxBus
import com.adityabugalia.baiscloginwithrxjava.utils.ValidationUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class LoginViewModel() : ViewModel(), View.OnClickListener {
    lateinit var inputModelList: InputModel
    var rxBus = RxBus()
    val compositeDisposable = CompositeDisposable()
    var resultLiveData: MutableLiveData<Any> =
        MutableLiveData()
        private set
    private var userName = ""
    private var password = ""

    fun onViewReady() {
        createData()
        initRxBusListener()
    }

    //creates dummy data for login UI
    private fun createData() {
        inputModelList = InputModel()
        inputModelList.add(
            InputModel.InputModelItem(
                "Welcome to FireFly!",
                Constants.LoginViewTypes.TITLE_TEXTVIEW,
                20
            )
        )
        inputModelList.add(
            InputModel.InputModelItem(
                "UserName",
                Constants.LoginViewTypes.USERNAME_EDITTEXT,
                10
            )
        )
        inputModelList.add(
            InputModel.InputModelItem(
                "Password",
                Constants.LoginViewTypes.PASSWORD_EDITTEXT,
                10
            )
        )
        inputModelList.add(
            InputModel.InputModelItem(
                "Login",
                Constants.LoginViewTypes.SUBMITE_BUTTON,
                20
            )
        )

    }

    //this recievs response from Mock layer.
    private fun initRxBusListener() {
        compositeDisposable.add(
            rxBus
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { receiverObject ->
                    if (receiverObject != null) {
                        if (receiverObject is String) {
                            Log.d("apiResponse", "" + receiverObject)
                            if (ValidationUtils.validateUserLoginResponse(receiverObject))
                                resultLiveData.postValue(receiverObject)
                        }

                    }
                })
        )
    }

    // for login button click in recyclerview
    override fun onClick(view: View?) {
        Log.d("submit", "")
        if (ValidationUtils.validateUserName(userName) && ValidationUtils.validatePassword(password)) {
            User.username = userName
            ApiCalls.performLogin(rxBus)
        } else {
            User.errorMessage = "validation failed"
            User.isSuccessful = "false"
            resultLiveData.postValue(User)
        }
    }


    fun onUserNameTextChange(str: String) {
        userName = str
        Log.d("username", "" + str)

    }


    fun onPasswordTextChange(str: String) {
        password = str
        Log.d("password", "" + str)
    }

}