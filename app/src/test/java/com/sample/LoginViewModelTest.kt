package com.sample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginRepo : LoginRepo
    val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initSetUp(){
        Dispatchers.setMain(testDispatcher)
        loginRepo = Mockito.mock(LoginRepo::class.java)
        loginViewModel = LoginViewModel(loginRepo)
    }

    @Test
    fun checkLoadingState_OnRequestInit_isTrue(){
        loginViewModel.setLoadingState(true)
        Truth.assertThat(loginViewModel.isLoading.value).isEqualTo(true)
    }


    @Test
    fun checkLoadingState_OnRequestComplete_isFalse(){
        loginViewModel.setLoadingState(false)
        Truth.assertThat(loginViewModel.isLoading.value).isFalse()
    }

    @Test
    fun onResponseReceived_checkFailedState_isError(){
        Mockito.`when`(loginRepo.validateLoginDetails("test@test.com","13")).thenReturn(null)
        loginViewModel.onLoginClick("test@test.com","13")
        Truth.assertThat(loginViewModel.error.value).isNotNull()
        Truth.assertThat(loginViewModel.isLoading.value).isEqualTo(false)
    }

    @Test
    fun onResponseReceived_checkSuccessState_isSuccess(){
        Mockito.`when`(loginRepo.validateLoginDetails("test@test.com","123")).thenReturn(LoginResponse())
        loginViewModel.onLoginClick("test@test.com","123")
        Truth.assertThat(loginViewModel.responseDataLD.value !=null)
        Truth.assertThat(loginViewModel.error.value.isNullOrBlank()).isEqualTo(true)
        Truth.assertThat(loginViewModel.isLoading.value).isEqualTo(false)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}