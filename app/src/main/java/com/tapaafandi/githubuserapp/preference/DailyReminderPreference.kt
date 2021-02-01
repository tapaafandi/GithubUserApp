package com.tapaafandi.githubuserapp.preference

import android.content.Context
import com.tapaafandi.githubuserapp.domain.model.DailyReminder

class DailyReminderPreference(context: Context) {
    companion object {
        const val PREFS_NAME = "daily_reminder_pref"
        private const val REMINDER = "isRemind"
    }

    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setReminderPref(value: DailyReminder) {
        val editor = preference.edit()
        editor.putBoolean(REMINDER, value.isReminderActive)
        editor.apply()
    }

    fun getReminderPref(): DailyReminder {
        val dailyReminder = DailyReminder()
        dailyReminder.isReminderActive = preference.getBoolean(REMINDER, false)
        return dailyReminder
    }
}