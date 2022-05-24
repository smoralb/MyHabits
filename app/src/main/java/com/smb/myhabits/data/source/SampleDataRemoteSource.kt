package com.smb.myhabits.data.source

import com.smb.core.data.Result
import com.smb.myhabits.domain.model.HabitListModel

interface SampleDataRemoteSource {

    suspend fun getSampleData(): Result<HabitListModel>
}