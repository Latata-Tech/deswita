package com.example.deswita

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.deswita.models.Destination
import com.example.deswita.models.Event
import com.example.deswita.ui.MainActivity
import com.example.deswita.ui.destination.DestinationActivity
import com.example.deswita.ui.event.EventActivity
import com.example.deswita.ui.mainmenu.search.SearchActivity
import com.example.deswita.ui.notification.NotificationActivity
import com.example.deswita.ui.reviews.AddReviewActivity
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

    @Test
    fun toAllMenu() {
        onView(withId(R.id.btnCanceDialog)).perform(ViewActions.click())
        onView(withId(R.id.eventFragment)).perform(ViewActions.click())
        onView(withId(R.id.storyFragment)).perform(ViewActions.click())
        onView(withId(R.id.profileFragment)).perform(ViewActions.click())
        onView(withId(R.id.homeFragment)).perform(ViewActions.click())
    }

    @Test
    fun toAllCategoryHome() {
        onView(withId(R.id.btnCanceDialog)).perform(ViewActions.click())
        onView(withId(R.id.chip_filter_recommended)).perform(ViewActions.click())
        onView(withId(R.id.chip_filter_popular)).perform(ViewActions.click())
        onView(withId(R.id.chip_filter_rating)).perform(ViewActions.click())
        onView(withId(R.id.chip_filter_favorite)).perform(ViewActions.click())
        onView(withId(R.id.chip_filter_all)).perform(ViewActions.click())
    }

    @Test
    fun checkDestination() {
        val destination = Destination(
            1,
            "des_1",
            "Danau Toba",
            "Bukit Barisan, Kabupaten Toba Samosir, Sumatera Utara.",
            false,
            3.5,
            2323.2
        )

        onView(withId(R.id.btnCanceDialog)).perform(ViewActions.click())
        onView(withText(destination.name)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(DestinationActivity::class.java.name))
        onView(withId(R.id.tvName)).check(matches(withText(destination.name)))
    }

    @Test
    fun showCalenderEvent() {
        onView(withId(R.id.btnCanceDialog)).perform(ViewActions.click())
        onView(withId(R.id.eventFragment)).perform(ViewActions.click())
        onView(withId(R.id.btnEventCalendar)).perform(ViewActions.click())
    }

    @Test
    fun testAddEvent() {
        onView(withId(R.id.btnCanceDialog)).perform(ViewActions.click())
        onView(withId(R.id.profileFragment)).perform(ViewActions.click())
        onView(withId(R.id.fabProfile)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun testAddStory(){
        onView(withId(R.id.btnCanceDialog)).perform(click())
        onView(withId(R.id.storyFragment)).perform(ViewActions.click())
        onView(withId(R.id.fabStory)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }


    @Test
    fun testAddReview(){
        val destination = Destination(
            1,
            "des_1",
            "Danau Toba",
            "Bukit Barisan, Kabupaten Toba Samosir, Sumatera Utara.",
            false,
            3.5,
            2323.2
        )

        onView(withId(R.id.btnCanceDialog)).perform(click())
        onView(withText(destination.name)).perform(click())
        onView(withId(R.id.nestedScrollView)).perform(ViewActions.swipeUp())
        onView(withId(R.id.btnGiveReview)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(DestinationActivity::class.java.name))
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @After
    fun tearDown()
    {
        Intents.release()
    }
}