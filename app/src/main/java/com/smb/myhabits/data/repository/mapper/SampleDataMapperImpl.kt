package com.smb.myhabits.data.repository.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.smb.myhabits.domain.model.HabitListModel
import com.smb.myhabits.domain.model.HabitModel

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