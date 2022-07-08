package com.smb.ft_main.data.repository.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.smb.ft_main.domain.model.HabitListModel
import com.smb.ft_main.domain.model.HabitModel

class SampleDataMapperImpl : SampleDataMapper {

    override fun toDomainModel(entity: QuerySnapshot): HabitListModel =
        HabitListModel(
            habitList = toHabitModel(entity.documents)
        )

    private fun toHabitModel(documentSnapshot: MutableList<DocumentSnapshot>): List<HabitModel> =
        documentSnapshot.map { doc ->
            HabitModel(
                name = doc["name"].toString(),
                description = doc["description"].toString()
            )
        }
}