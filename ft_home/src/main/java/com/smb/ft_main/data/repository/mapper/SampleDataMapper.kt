package com.smb.ft_main.data.repository.mapper

import com.google.firebase.firestore.QuerySnapshot
import com.smb.ft_main.domain.model.HabitListModel

interface SampleDataMapper {

    fun toDomainModel(entity: QuerySnapshot): HabitListModel
}