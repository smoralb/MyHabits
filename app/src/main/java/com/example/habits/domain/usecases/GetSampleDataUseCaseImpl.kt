package com.example.habits.domain.usecases

import com.example.habits.domain.model.SampleChildModel
import com.example.habits.domain.repository.SampleDataRepository
import com.example.core.data.Result

class GetSampleDataUseCaseImpl(
    private val repository: SampleDataRepository
) : GetSampleDataUseCase {

    override suspend fun invoke(params: Unit): Result<SampleChildModel> =
        repository.getSampleData()
}