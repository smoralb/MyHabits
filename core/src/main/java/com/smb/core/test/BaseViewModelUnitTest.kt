package com.smb.core.test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class, CoroutinesTestExtension::class)
open class BaseViewModelUnitTest : BaseUnitTest()