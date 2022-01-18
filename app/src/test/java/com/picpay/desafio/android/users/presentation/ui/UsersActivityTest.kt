package com.picpay.desafio.android.users.presentation.ui


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import com.picpay.desafio.android.core.ui.ViewState
import com.picpay.desafio.android.users
import com.squareup.picasso.Picasso
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class UsersActivityTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var activity: ActivityController<UsersActivity>

    @Before
    fun setup() {
        mockkStatic(Picasso::class)
        val picassoMock = mockkClass(Picasso::class, relaxed = true)
        coEvery {
            Picasso.get()
        } returns picassoMock

        activity = buildActivity(UsersActivity::class.java)
    }

    @Test
    fun `when creating the activity then the state must be skeleton`() {
        val scenario = ActivityScenario.launch(UsersActivity::class.java)

        scenario.onActivity { activity ->
            assert(activity.binding.flipper.childCount == ViewState.values().size)
            assert(activity.binding.flipper.displayedChild == ViewState.SKELETON.state)
        }
    }

    @Test
    fun `when the viewModel's adapter receive data then the activiy's state must change to normal and populate the recycler view`() {

        val scenario = ActivityScenario.launch(UsersActivity::class.java)

        scenario.onActivity { activity ->
            activity.viewModel.state.value = ViewState.NORMAL
            activity.viewModel.adapter.users = users
            assert(activity.binding.normal.recyclerView.adapter?.itemCount == 8)
            assert(activity.binding.flipper.displayedChild == ViewState.NORMAL.state)
        }
    }

    @Test
    fun `when viewModel receive an error then the activity must change to error state`() {

        val scenario = ActivityScenario.launch(UsersActivity::class.java)

        scenario.onActivity { activity ->
            activity.viewModel.state.value = ViewState.ERROR
            assert(activity.binding.flipper.displayedChild == ViewState.ERROR.state)
        }
    }

}