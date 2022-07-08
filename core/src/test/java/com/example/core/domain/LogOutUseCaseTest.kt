package com.example.core.domain

import com.google.firebase.auth.FirebaseAuth
import com.smb.core.domain.LogOutUseCase
import com.smb.core.domain.LogOutUseCaseImpl
import com.smb.core.test.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.any

@ExperimentalCoroutinesApi
class LogOutUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var logOutUseCase: LogOutUseCase

    @BeforeEach
    fun setUp() {
        logOutUseCase = LogOutUseCaseImpl(firebaseAuth)
    }

    @Test
    fun `logOut should sign out`() {
        runBlockingTest {
            val result = logOutUseCase(Unit)

            Assertions.assertTrue(result.isSuccess)
        }
    }

}