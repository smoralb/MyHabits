package com.smb.myhabits.domain.usecases

import com.smb.core.data.Result
import com.smb.myhabits.domain.model.HabitListModel
import com.smb.myhabits.domain.repository.SampleDataRepository

class GetSampleDataUseCaseImpl(
    private val repository: SampleDataRepository
) : GetSampleDataUseCase {

    override suspend fun invoke(params: Unit): Result<HabitListModel> =
        repository.getSampleData()
}