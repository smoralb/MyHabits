package com.smb.ft_home.domain.model

data class CreateTaskModel(
    var name: String,
    var description: String,
    var hour: String,
    var min: String
)