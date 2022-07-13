package com.smb.ft_main.domain.repository

import com.smb.core.data.Result
import com.smb.ft_main.domain.model.HabitListModel

interface HomeRepository {

    suspend fun getSampleData(): Result<HabitListModel>
}