package com.smb.ft_main.domain.usecases

import com.smb.core.data.Result
import com.smb.ft_main.domain.model.HabitListModel
import com.smb.ft_main.domain.repository.HomeRepository

class GetSampleDataUseCaseImpl(
    private val repository: HomeRepository
) : GetSampleDataUseCase {

    override suspend fun invoke(params: Unit): Result<HabitListModel> =
        repository.getSampleData()
}