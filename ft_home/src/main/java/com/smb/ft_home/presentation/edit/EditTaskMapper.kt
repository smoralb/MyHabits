package com.smb.ft_home.presentation.edit

import com.smb.ft_home.domain.model.UpdateTaskModel

interface EditTaskMapper {
    fun toEditModel(id: String, title: String, description: String): UpdateTaskModel
}

class EditTaskMapperImpl() : EditTaskMapper {

    override fun toEditModel(id: String, title: String, description: String) =
        UpdateTaskModel(
            id = id,
            name = title,
            description = description
        )
}