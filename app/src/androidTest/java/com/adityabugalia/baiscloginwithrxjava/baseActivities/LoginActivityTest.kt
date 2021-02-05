package com.adityabugalia.baiscloginwithrxjava.baseActivities

import android.app.Activity
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.adityabugalia.baiscloginwithrxjava.R
import com.adityabugalia.baiscloginwithrxjava.adapters.LoginAdapter
import org.hamcrest.Matcher
import org.hamcrest.Matchers

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)

    private val username = "timwe"
    private val password = "asad"


    @Before
    fun setUp() {
    }

    fun clickItemWithId(id: Int, intcase: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return ""
            }

            override fun perform(uiController: UiController, view: View) {
                when (intcase) {

                    0 -> {
                        val v = view.findViewById<View>(id) as EditText
                        v.setText(username)
                    }
                    1 -> {
                        val v = view.findViewById<View>(id) as EditText
                        v.setText(password)
                    }
                    2 -> {
                        val v = view.findViewById<View>(id) as Button
                        v.performClick()
                    }

                }


            }
        }
    }

    @Test
    fun performLogin() {
        /*
        * input username
        * input password
        * click login button
        * */

        Espresso.onView(withId(R.id.loginRecyclerView))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<LoginAdapter.ItemViewHolder>(
                        1,
                        clickItemWithId(R.id.inputEditText, 0)
                    )
            )

        Espresso.onView(withId(R.id.loginRecyclerView))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<LoginAdapter.ItemViewHolder>(
                        2,
                        clickItemWithId(R.id.inputEditText, 1)
                    )
            )

        Espresso.onView(withId(R.id.loginRecyclerView))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<LoginAdapter.ItemViewHolder>(
                        3,
                        clickItemWithId(R.id.submitButton, 2)
                    )
            )

    }

    @After
    fun tearDown() {
    }
}