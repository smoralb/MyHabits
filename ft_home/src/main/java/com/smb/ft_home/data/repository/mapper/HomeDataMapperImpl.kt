package com.smb.ft_home.data.repository.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.smb.ft_home.domain.model.HabitListModel
import com.smb.ft_home.domain.model.HabitModel

const val NAME = "name"
const val DESCRIPTION = "description"

class HomeDataMapperImpl : HomeDataMapper {

    override fun toDomainModel(entity: QuerySnapshot): HabitListModel =
        HabitListModel(
            habitList = toHabitModel(entity.documents)
        )

    private fun toHabitModel(documentSnapshot: MutableList<DocumentSnapshot>): List<HabitModel> =
        documentSnapshot.map {
            HabitModel(
                id = it.id,
                name = it.get(NAME) as? String,
                description = it[DESCRIPTION] as? String,
            )
        }
}