package com.sample

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class LoginRepoTest {
    @Mock
    lateinit var repoInstance: LoginRepo

    @Before
    fun init() {
        repoInstance = Mockito.mock(LoginRepo::class.java)

    }
    @Test
    fun validateLogin_isSuccess() {

        runBlocking {
            Mockito.`when`(repoInstance.validateLoginDetails("test@test.com","123")).thenReturn(
                LoginResponse()
            )
        }

        runBlocking {
            Truth.assertThat(repoInstance.validateLoginDetails("test@test.com","123"))
                .isEqualTo(LoginResponse())
        }
    }

    @Test
    fun validateLogin_isFailed() {

        runBlocking {
            Mockito.`when`(repoInstance.validateLoginDetails("test@test.com","12")).thenReturn(
                null
            )
        }

        runBlocking {
            Truth.assertThat(repoInstance.validateLoginDetails("test@test.com","12"))
                .isEqualTo(null)
        }
    }
}