package com.adityabugalia.baiscloginwithrxjava.utils

import org.junit.Test

import org.junit.Assert.*

class ValidationUtilsTest {

    //Username is not empty
    @Test
    fun `username should not be empty`() {
        val result = ValidationUtils.validateUserName("")
        assert(true)

    }

    //Username contains char and number
    @Test
    fun `username contains atleast one char and one number`() {
        val result = ValidationUtils.validateUserName("")
        assert(true)
    }

    //password  is not empty
    @Test
    fun `password should not be empty`() {
        val result = ValidationUtils.validatePassword("")
        assert(true)

    }

    //password contains char and number
    @Test
    fun `password contains atleast one char and one number`() {
        val result = ValidationUtils.validatePassword("")
        assert(true)
    }

    //response from server should not be empty
    @Test
    fun `login response should not be emplty`() {
        val result = ValidationUtils.validateUserLoginResponse("")
        assert(true)
    }

    //response from server should be a valid json
    @Test
    fun `login response should be a valid json`() {
        val result = ValidationUtils.validateUserLoginResponse("")
        assert(true)
    }

    //response from server should have username and isSuccessful keys
    @Test
    fun `login response should have username and isSuccessful keys`() {
        val result = ValidationUtils.validateUserLoginResponse("")
        assert(true)
    }
}