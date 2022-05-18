package com.smb.myhabits.domain.usecases

import com.smb.myhabits.domain.model.SampleChildModel
import com.smb.myhabits.domain.repository.SampleDataRepository
import com.smb.core.data.Result

class GetSampleDataUseCaseImpl(
    private val repository: SampleDataRepository
) : GetSampleDataUseCase {

    override suspend fun invoke(params: Unit): Result<SampleChildModel> =
        repository.getSampleData()
}