package com.adityabugalia.baiscloginwithrxjava.utils

import android.util.Log
import com.adityabugalia.baiscloginwithrxjava.data.User

object ValidationUtils {
    private val patternForInput = Regex("^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+\$")

    /*
    * The username should not be empty
    * The username should have atleast one char and one number
    * */
    fun validateUserName(input: String): Boolean {

        return input.length >= 2 && input.matches(patternForInput)
    }

    /*
* The password should not be empty
* The password should have atleast one char and one number
* */
    fun validatePassword(input: String): Boolean {

        return input.length >= 2 && input.matches(patternForInput)
    }

    /*
* The response should not be empty
* The response should be a valid Response
* The response should have username and isSuccessful keys.
* */
    fun validateUserLoginResponse(response: String): Boolean {

        if (response.length >= 2) {

            try {
                val responseObj =
                    ((((response.replace("{", "")).replace("}", ""))).replace(",", ":")).split(":")
                        .toTypedArray()
                User.isSuccessful = responseObj[1].trim()
                if (User.getSuccessful() && User.username.length > 0) {
                    return true
                }
            } catch (e: Exception) {
                User.errorMessage = "Did not expect That...."
            }
        }
        return false

    }

}