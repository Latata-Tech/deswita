package com.example.deswita

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.deswita.ui.MainActivity
import com.example.deswita.ui.mainmenu.search.SearchActivity
import com.example.deswita.ui.notification.NotificationActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule @JvmField
    val activityTestResult  = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp()
    {
        Intents.init()
    }

    @Test
    fun toNotificationActivity()
    {
        onView(withId(R.id.btnCanceDialog)).perform(ViewActions.click())
        onView(withId(R.id.btnAppBarNotification)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(NotificationActivity::class.java.name))
    }

    @Test
    fun toSearchActivity()
    {
        onView(withId(R.id.btnCanceDialog)).perform(ViewActions.click())
        onView(withId(R.id.btnAppBarSearch)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(SearchActivity::class.java.name))
    }

    @After
    fun tearDown()
    {
        Intents.release()
    }
}