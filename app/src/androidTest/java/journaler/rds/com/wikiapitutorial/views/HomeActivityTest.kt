package journaler.rds.com.wikiapitutorial.views

import android.app.Instrumentation
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import journaler.rds.com.wikiapitutorial.R
import journaler.rds.com.wikiapitutorial.R.id.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest{
    @Rule @JvmField
    var activityTestRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    @Test
    fun testToolbarTitle(){
        onView(withId(bottomFavoritesID))
                .perform(click())

        onView(
                allOf(
                        isAssignableFrom(TextView::class.java),
                        withParent (isAssignableFrom(Toolbar::class.java))))
                .check(matches(withText(R.string.favorite_subject)))


        onView(withId(bottomSubjectsID))
                .perform(click())

        val title = InstrumentationRegistry.getTargetContext().getString(R.string.wiki_subject)
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(checkToolbarTitle(title)))


    }


    private fun checkToolbarTitle(expectedTitle: CharSequence):Matcher<View>{
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java){
            override fun describeTo(description: Description?) {
                description?.appendText("with Toolbar title: $expectedTitle")
            }

            override fun matchesSafely(toolbar: Toolbar?): Boolean {
                return expectedTitle == toolbar?.title
            }

        }

    }


    @Test
    fun testRequestAPI(){
        onView(withId(searchMenuID))
                .perform(click())
                .perform(typeText("Indonesia"))
                .perform(click())

//     Thread.sleep(1000)

        val idlingResource = WikiSubjectIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)

    onView(withId(rvQueryListID))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        IdlingRegistry.getInstance().unregister(idlingResource)

//        Espresso.pressBack()
//
//
//        onView(withId(searchMenuID))
//                .perform(click())
//                .perform(c)
//                .perform(doubleClick())
//                .perform(typeTextIntoFocusedView("Google"))
//                .perform(click())
//
//        val idlingResource2 = WikiSubjectIdlingResource()
//        IdlingRegistry.getInstance().register(idlingResource2)
//
//        onView(withId(rvQueryListID))
//                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
//
//        IdlingRegistry.getInstance().unregister(idlingResource2)


    }








}