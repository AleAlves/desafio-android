package com.picpay.desafio.android.users.presentation.ui

import android.app.Activity
import android.view.View
import com.squareup.picasso.Picasso
import io.mockk.coEvery
import io.mockk.mockkClass
import io.mockk.mockkStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class UserItemViewTest {

    private val activitiyController: ActivityController<Activity> =
        Robolectric.buildActivity(Activity::class.java)

    private val activity: Activity = activitiyController.get()

    private val usersItemView = UserItemView(activity)

    @Before
    fun setup() {
        mockkStatic(Picasso::class)
        val picassoMock = mockkClass(Picasso::class, relaxed = true)
        coEvery {
            Picasso.get()
        } returns picassoMock
    }

    @Test
    fun `when creating the acticvity then no user provided should keep the user custom item view invisible`() {
        with(usersItemView) {
            assert(binding.progressBar.visibility == View.GONE)
        }
    }

    @Test
    fun `when the activity created then an user provided should make the user custom view item visible`() {
        with(usersItemView) {
            user = user
            assert(binding.progressBar.visibility == View.VISIBLE)
            assert(binding.name.visibility == View.VISIBLE)
            assert(binding.username.visibility == View.VISIBLE)
            assert(binding.picture.visibility == View.VISIBLE)
        }
    }
}