package dev.elvir.homeworkforcinemood

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UiTests {

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun setInputText() {
        onView(withId(R.id.et_user_search)).perform(typeText("Elik"))
        pauseTestFor(500);
        onView(withText("Elik")).check(matches(isDisplayed()))



//        onView(withId(R.id.set_user_name)).perform(click())
//        onView(withText("Hello Vivek Maskara!")).check(matches(isDisplayed()))
    }

    private fun pauseTestFor(milliseconds: Long) {
        try {
            Thread.sleep(milliseconds)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}