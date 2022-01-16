package com.picpay.desafio.android.users.data


import com.picpay.desafio.android.users
import com.picpay.desafio.android.users.domain.model.User
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception


class UserRemoteDataSourceImplTest {

    private lateinit var remote: UserRemoteDataSource
    private val service: PicPayService = mockk()
    private val call = mockk<Call<List<User>>>()
    private val response = mockk<Response<List<User>>>()

    @Before
    fun setUp() {
        remote = UserRemoteDataSourceImpl(service)

        every { service.getUsers() } returns call

        every { call.execute() } returns response

    }

    @Test
    fun `when requested the service then should return a user list successfully`() {

        every { response.body() } returns users

        every { response.isSuccessful } returns true

        val data = remote.fetch()

        assert(data?.isNotEmpty() == true)
    }


    @Test
    fun `when requested the service then should return a empty user list successfully`() {

        every { response.body() } returns listOf()

        every { response.isSuccessful } returns true

        val data = remote.fetch()

        assert(data?.isEmpty() == true)
    }

    @Test
    fun `when the response isn't successfully then should return an exception`() {

        every { response.body() } returns null

        every { response.code() } returns 500

        every { response.isSuccessful } returns false

        try{
            remote.fetch()
        }catch (e: Exception){
            assert(e.toString() == "java.lang.Exception: 500")
        }
    }
}