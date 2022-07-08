package com.smb.ft_main.data.source

import com.smb.core.data.Result
import com.smb.ft_main.domain.model.HabitListModel

interface SampleDataRemoteSource {

    suspend fun getSampleData(): Result<HabitListModel>
}