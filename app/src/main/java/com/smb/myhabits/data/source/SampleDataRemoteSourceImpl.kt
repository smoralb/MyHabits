package com.smb.myhabits.data.source

import com.google.firebase.firestore.FirebaseFirestore
import com.smb.core.data.Result
import com.smb.core.data.safeApiCall
import com.smb.myhabits.data.repository.mapper.SampleDataMapper
import com.smb.myhabits.domain.model.HabitListModel

private const val COLLECTION_DB = "habits"

class SampleDataRemoteSourceImpl(
    private val mapper: SampleDataMapper,
    private val fireStore: FirebaseFirestore
) : SampleDataRemoteSource {

    override suspend fun getSampleData(): Result<HabitListModel> =
        safeApiCall(
            apiCall = {
                fireStore
                    .collection(COLLECTION_DB)
                    .get()
            }, mapper = {
                mapper.toDomainModel(it)
            }
        )
}