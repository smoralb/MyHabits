package com.smb.myhabits.domain.usecases

import com.smb.core.domain.UseCase
import com.smb.myhabits.domain.model.HabitListModel

interface GetSampleDataUseCase : UseCase<Unit, HabitListModel>