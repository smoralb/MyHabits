package com.smb.ft_auth.domain.mocks

import com.smb.core.data.Result

data class CreateNewAccountDataMock(
    val result: Result<Unit>,
    val email: String,
    val validEmail: Boolean,
    val password: String,
    val validPassword: Boolean
)