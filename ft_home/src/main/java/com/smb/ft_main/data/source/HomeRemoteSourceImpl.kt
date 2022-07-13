package com.smb.ft_main.data.source

import com.google.firebase.firestore.FirebaseFirestore
import com.smb.core.data.Result
import com.smb.core.data.safeApiCall
import com.smb.ft_main.data.repository.mapper.HomeDataMapper
import com.smb.ft_main.domain.model.HabitListModel

private const val COLLECTION_DB = "habits"

class HomeRemoteSourceImpl(
    private val mapper: HomeDataMapper,
    private val fireStore: FirebaseFirestore
) : HomeRemoteSource {

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