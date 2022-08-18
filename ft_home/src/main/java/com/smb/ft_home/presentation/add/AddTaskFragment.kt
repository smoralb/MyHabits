package com.smb.ft_home.presentation.add

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_DAY
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_home.BR
import com.smb.ft_home.R
import com.smb.ft_home.databinding.FragmentAddBinding
import com.smb.ft_home.presentation.add.AddTaskState.AddNewTask
import com.smb.ft_home.presentation.add.AddTaskState.Loading
import com.smb.ft_home.presentation.add.AddTaskState.NavigateUp
import com.smb.ft_home.presentation.add.AddTaskState.ShowError
import com.smb.ft_notifications.AlarmBroadcastReceiver
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

@SuppressLint("UnspecifiedImmutableFlag")
class AddTaskFragment : BaseFragment<AddTaskState, FragmentAddBinding, AddTaskViewModel>(
    R.layout.fragment_add, BR.viewModel
) {

    override val viewModel: AddTaskViewModel by viewModel()
    private val calendar: Calendar = Calendar.getInstance()
    private var alarmMgr: AlarmManager? = null
    private var pendingIntent: PendingIntent? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as? AlarmManager?
        val alarmIntent = Intent(this.context, AlarmBroadcastReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(
            context,
            102,
            alarmIntent,
            FLAG_UPDATE_CURRENT
        )
        setUpNumberPicker()
    }

    override fun checkViewState(state: AddTaskState) {
        when (state) {
            is Loading -> binding.plItemsLoader.visibility = View.VISIBLE
            is ShowError -> Toast.makeText(activity, state.message, Toast.LENGTH_SHORT).show()
            is NavigateUp -> navigateUp()
            is AddNewTask -> {
                setUpAlarm(state.timeInMillis)
                navigateUp()
            }
        }
    }

    private fun setUpNumberPicker() {
        binding.npHours.apply {
            minValue = 0
            maxValue = 23
            value = calendar.get(Calendar.HOUR_OF_DAY)
            viewModel.hour update value
            setOnValueChangedListener { _, _, newValue -> viewModel.hour update newValue }
        }

        binding.npMinutes.apply {
            minValue = 0
            maxValue = 59
            value = calendar.get(Calendar.MINUTE)
            viewModel.minutes update value
            setOnValueChangedListener { _, _, newValue -> viewModel.minutes update newValue }
        }
    }

    private fun setUpAlarm(timeInMillis: Long) {
        alarmMgr?.setRepeating(
            RTC_WAKEUP,
            timeInMillis,
            INTERVAL_DAY,
            pendingIntent
        )
    }
}