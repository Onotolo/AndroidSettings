package onotolo.android.settings

import android.app.Activity
import android.content.Context

@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
abstract class SettingsProvider {

    abstract var PREFS_NAME: String

    fun <T>setValue(context: Context?, settingId: String, value: T) {

        context?.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE)?.edit()

            ?.apply{
                when (value) {
                    is Boolean -> putBoolean(settingId, value)
                    is Int -> putInt(settingId, value)
                    is String -> putString(settingId, value)
                    is Float -> putFloat(settingId, value)
                    is Long -> putLong(settingId, value)
                    is MutableSet<*> -> putStringSet(settingId, value as MutableSet<String>)
                }
                apply()
            }
    }

    fun <T>getValue(context: Context?, settingId: String, defaultValue: T): T {

        var value: T = defaultValue

        context?.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE)?.apply {
            value = when (defaultValue) {
                is Boolean -> getBoolean(settingId, defaultValue)
                is Int -> getInt(settingId, defaultValue)
                is String -> getString(settingId, defaultValue)
                is Float -> getFloat(settingId, defaultValue)
                is Long -> getLong(settingId, defaultValue)
                is MutableSet<*> -> getStringSet(settingId, defaultValue as MutableSet<String>)
                else -> defaultValue
            } as T
        }
        return value
    }
}