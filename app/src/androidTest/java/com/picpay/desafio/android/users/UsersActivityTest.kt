package com.picpay.desafio.android.users


import android.widget.ViewFlipper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.R
import com.picpay.desafio.android.core.ui.ViewState
import com.picpay.desafio.android.users.presentation.ui.UsersActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {

    }

    @Test
    fun validate_title_for_the_activity() {
        launchActivity<UsersActivity>().apply {
            val expectedTitle = context.getString(R.string.title)
            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validate_skeleton_state_for_the_activity_launch() = runBlockingTest {
        launchActivity<UsersActivity>().apply {
            onView(ViewMatchers.withId(R.id.flipper)).check { view, _ ->
                val flipper = view as ViewFlipper
                assert(flipper.displayedChild == ViewState.NORMAL.state)
            }
            onView(ViewMatchers.withId(R.id.user_list_progress_bar)).check(matches(isDisplayed()))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validate_content_setup() = runBlockingTest {
        launchActivity<UsersActivity>().apply {
            onView(ViewMatchers.withId(R.id.recyclerView)).check { view, _ ->
                val recyclerView = view as RecyclerView
                assert(recyclerView.adapter?.itemCount != 0)
            }
        }
    }

    @After
    fun finish() {
    }
}