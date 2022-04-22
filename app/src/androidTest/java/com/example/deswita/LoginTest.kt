package com.example.deswita

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.deswita.ui.MainActivity
import com.example.deswita.ui.auth.LoginActivity
import com.example.deswita.ui.auth.RegisterActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest {
    @Rule @JvmField
    var activityTestResult = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun clickLoginButton(){
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))
    }

    @Before
    fun setUp()
    {
        Intents.init()
    }

    @Test
    fun toHome()
    {
        onView(withId(R.id.username)).perform(ViewActions.typeText("admin"))
        onView(withId(R.id.password)).perform(ViewActions.typeText("admin"))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.btnLogin)).perform(ViewActions.click())
        intended(hasComponent(MainActivity::class.java.name))
    }

    @Test
    fun toRegisterActivity()
    {
        onView(withId(R.id.textRegister)).perform(click())
        intended(hasComponent(RegisterActivity::class.java.name))
    }

    @After
    fun tearDown()
    {
        Intents.release()
    }
}