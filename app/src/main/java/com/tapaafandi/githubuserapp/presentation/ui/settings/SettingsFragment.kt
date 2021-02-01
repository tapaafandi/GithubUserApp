package com.tapaafandi.githubuserapp.presentation.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.tapaafandi.githubuserapp.R
import com.tapaafandi.githubuserapp.alarm.AlarmReceiver
import com.tapaafandi.githubuserapp.databinding.FragmentSettingsBinding
import com.tapaafandi.githubuserapp.domain.model.DailyReminder
import com.tapaafandi.githubuserapp.preference.DailyReminderPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var dailyReminder: DailyReminder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        alarmReceiver = AlarmReceiver()

        val dailyReminderPreference = DailyReminderPreference(requireContext())

        binding.swDailyReminder.isChecked = dailyReminderPreference.getReminderPref().isReminderActive

        binding.swDailyReminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveReminderState(true)
                alarmReceiver.setRepeatingAlarm(requireContext(), AlarmReceiver.TYPE_REPEATING, "09:00", "Github Daily Reminder!")
            } else {
                saveReminderState(false)
                alarmReceiver.cancelAlarm(requireContext())
            }
        }
    }

    private fun saveReminderState(state: Boolean) {
        val dailyReminderPreference = DailyReminderPreference(requireContext())
        dailyReminder = DailyReminder()
        dailyReminder.isReminderActive = state
        dailyReminderPreference.setReminderPref(dailyReminder)
    }
}