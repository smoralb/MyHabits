package com.smb.ft_auth.data

import com.smb.core.data.Result
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseUnitTest
import com.smb.ft_auth.data.repository.AuthRepositoryImpl
import com.smb.ft_auth.data.source.AuthRemoteDataSource
import com.smb.ft_auth.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class AuthRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var remoteSource: AuthRemoteDataSource

    private lateinit var repository: AuthRepository

    @BeforeEach
    fun setUp() {
        repository = AuthRepositoryImpl(remoteSource)
    }

    @TestFactory
    fun `createNewAccount should return`() = listOf(
        Result.Success(Unit), Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(remoteSource.createNewAccount(EMPTY_STRING, EMPTY_STRING)).thenReturn(
                    testCase
                )

                val result = repository.createNewAccount(EMPTY_STRING, EMPTY_STRING)
                verify(remoteSource).createNewAccount(EMPTY_STRING, EMPTY_STRING)
                assertEquals(testCase, result)
            }
            clearInvocations(remoteSource)
        }
    }

    @TestFactory
    fun `login should return`() = listOf(
        Result.Success(Unit), Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(remoteSource.login(EMPTY_STRING, EMPTY_STRING)).thenReturn(
                    testCase
                )

                val result = repository.login(EMPTY_STRING, EMPTY_STRING)
                verify(remoteSource).login(EMPTY_STRING, EMPTY_STRING)
                assertEquals(testCase, result)
            }
            clearInvocations(remoteSource)
        }
    }

    @TestFactory
    fun `recoverPassword should return`() = listOf(
        Result.Success(Unit), Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(remoteSource.recoverPassword(EMPTY_STRING)).thenReturn(
                    testCase
                )

                val result = repository.recoverPassword(EMPTY_STRING)
                verify(remoteSource).recoverPassword(EMPTY_STRING)
                assertEquals(testCase, result)
            }
            clearInvocations(remoteSource)
        }
    }
}