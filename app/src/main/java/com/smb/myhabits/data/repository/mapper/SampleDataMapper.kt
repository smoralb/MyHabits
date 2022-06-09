package com.smb.myhabits.data.repository.mapper

import com.google.firebase.firestore.QuerySnapshot
import com.smb.myhabits.domain.model.HabitListModel

interface SampleDataMapper {

    fun toDomainModel(entity: QuerySnapshot): HabitListModel
}