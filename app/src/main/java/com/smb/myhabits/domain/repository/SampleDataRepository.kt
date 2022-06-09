package com.smb.myhabits.domain.repository

import com.smb.core.data.Result
import com.smb.myhabits.domain.model.HabitListModel

interface SampleDataRepository {

    suspend fun getSampleData(): Result<HabitListModel>
}