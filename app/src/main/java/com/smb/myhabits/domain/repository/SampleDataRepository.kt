package com.smb.myhabits.domain.repository

import com.smb.myhabits.domain.model.SampleChildModel
import com.smb.core.data.Result

interface SampleDataRepository {

    suspend fun getSampleData(): Result<SampleChildModel>
}