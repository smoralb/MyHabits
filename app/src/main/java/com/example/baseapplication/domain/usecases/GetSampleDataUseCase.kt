package com.example.baseapplication.domain.usecases

import com.example.baseapplication.domain.model.SampleChildModel
import com.example.core.domain.UseCase

interface GetSampleDataUseCase: UseCase<Unit, SampleChildModel>