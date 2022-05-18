package com.smb.myhabits.domain.usecases

import com.smb.myhabits.domain.model.SampleChildModel
import com.smb.core.domain.UseCase

interface GetSampleDataUseCase: UseCase<Unit, SampleChildModel>