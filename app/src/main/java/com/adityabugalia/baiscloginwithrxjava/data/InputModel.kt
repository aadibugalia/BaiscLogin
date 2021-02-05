package com.adityabugalia.baiscloginwithrxjava.data

import com.adityabugalia.baiscloginwithrxjava.utils.Constants

class InputModel : ArrayList<InputModel.InputModelItem>(){
    data class InputModelItem(
        var displayText: String,
        var viewTag: Constants.LoginViewTypes,
        var maxLength: Int
    )
}