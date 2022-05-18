package com.smb.myhabits.data.source

import com.smb.myhabits.domain.model.SampleChildModel
import com.smb.core.data.Result

interface SampleDataRemoteSource {

    suspend fun getSampleData(): Result<SampleChildModel>
}