package com.smb.ft_home.data.repository.mapper

import com.google.firebase.firestore.QuerySnapshot
import com.smb.ft_home.domain.model.HabitListModel

interface HomeDataMapper {

    fun toDomainModel(entity: QuerySnapshot): HabitListModel
}