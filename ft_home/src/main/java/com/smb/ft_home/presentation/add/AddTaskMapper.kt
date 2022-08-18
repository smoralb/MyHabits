package com.smb.ft_home.presentation.add

import java.util.Calendar

interface AddTaskMapper {
    fun mapTimeToMillis(hours: Int, minutes: Int): Long
}

class AddTaskMapperImpl() : AddTaskMapper {

    override fun mapTimeToMillis(hours: Int, minutes: Int): Long =
        Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, minutes)
            set(Calendar.SECOND, 0)
        }.timeInMillis
}