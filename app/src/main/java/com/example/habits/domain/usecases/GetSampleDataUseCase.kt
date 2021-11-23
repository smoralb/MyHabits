package com.example.habits.domain.usecases

import com.example.habits.domain.model.SampleChildModel
import com.example.core.domain.UseCase

interface GetSampleDataUseCase: UseCase<Unit, SampleChildModel>