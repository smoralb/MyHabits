package com.smb.ft_home.data.source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.smb.core.data.Result
import com.smb.core.data.safeApiCall
import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_home.data.repository.mapper.HomeDataMapper
import com.smb.ft_home.domain.model.CreateTaskModel
import com.smb.ft_home.domain.model.HabitListModel
import com.smb.ft_home.domain.model.UpdateTaskModel

private const val COLLECTION_DB = "habits"
private const val COLLECTION_TASKS = "tasks"

class HomeRemoteSourceImpl(
    private val mapper: HomeDataMapper,
    private val fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : HomeRemoteSource {

    override suspend fun getTasks(): Result<HabitListModel> =
        safeApiCall(
            apiCall = {
                fireStore
                    .collection(COLLECTION_DB)
                    .document(firebaseAuth.currentUser?.uid ?: EMPTY_STRING)
                    .collection(COLLECTION_TASKS)
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
                    .document(firebaseAuth.currentUser?.uid ?: EMPTY_STRING)
                    .collection(COLLECTION_TASKS)
                    .add(task)
            },
            mapper = {}
        )

    override suspend fun deleteTask(documentId: String): Result<Unit> =
        safeApiCall(
            apiCall = {
                fireStore
                    .collection(COLLECTION_DB)
                    .document(firebaseAuth.currentUser?.uid ?: EMPTY_STRING)
                    .collection(COLLECTION_TASKS)
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
                    .document(firebaseAuth.currentUser?.uid ?: EMPTY_STRING)
                    .collection(COLLECTION_TASKS)
                    .document(task.id)
                    .set(task)
            },
            mapper = {}
        )
}