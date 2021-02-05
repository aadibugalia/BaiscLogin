package com.adityabugalia.baiscloginwithrxjava.utils

class Constants {
    enum class LoginViewTypes (val title: String, val index: Int){
        TITLE_TEXTVIEW("titleTextView", 0),
        USERNAME_EDITTEXT("usernameEditText", 1),
        PASSWORD_EDITTEXT("passwordEditText", 2),
        SUBMITE_BUTTON("submitButton", 3)
    }
}