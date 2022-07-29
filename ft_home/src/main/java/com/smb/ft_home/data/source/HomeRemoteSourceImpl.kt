package com.smb.ft_home.data.source

import com.google.firebase.firestore.FirebaseFirestore
import com.smb.core.data.Result
import com.smb.core.data.safeApiCall
import com.smb.ft_home.data.repository.mapper.HomeDataMapper
import com.smb.ft_home.domain.model.CreateTaskModel
import com.smb.ft_home.domain.model.HabitListModel
import com.smb.ft_home.domain.model.UpdateTaskModel

private const val COLLECTION_DB = "habits"

class HomeRemoteSourceImpl(
    private val mapper: HomeDataMapper,
    private val fireStore: FirebaseFirestore
) : HomeRemoteSource {

    override suspend fun getTasks(): Result<HabitListModel> =
        safeApiCall(
            apiCall = {
                fireStore
                    .collection(COLLECTION_DB)
                    .get()
            }, mapper = {
                mapper.toDomainModel(it)
            }
        )

    override suspend fun createTask(task: CreateTaskModel): Result<Unit> =
        safeApiCall(
            apiCall = {
                fireStore
                    .collection(COLLECTION_DB)
                    .add(task)
            },
            mapper = {}
        )

    override suspend fun deleteTask(documentId: String): Result<Unit> =
        safeApiCall(
            apiCall = {
                fireStore
                    .collection(COLLECTION_DB)
                    .document(documentId)
                    .delete()
            },
            mapper = {}
        )

    override suspend fun updateTask(task: UpdateTaskModel): Result<Unit> =
        safeApiCall(
            apiCall = {
                fireStore
                    .collection(COLLECTION_DB)
                    .document(task.id)
                    .set(task)
            },
            mapper = {}
        )
}