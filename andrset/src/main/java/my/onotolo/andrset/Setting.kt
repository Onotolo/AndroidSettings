package my.onotolo.andrset

import android.content.Context

abstract class Setting<T> {

    abstract val settingNameResId: Int
    abstract val descriptionResId: Int?
    abstract val settingsProvider: SettingsProvider

    fun getName(context: Context?): String? {
        return context?.getString(settingNameResId)
    }
    fun getDescription(context: Context?): String? {
        val resId = descriptionResId ?: return null
        return context?.getString(resId)
    }

    abstract val id: String

    abstract val defaultValue: T

    open operator fun get(context: Context?): T {
        return settingsProvider.getValue(context, id, defaultValue)
    }

    open operator fun set(context: Context?, value: T) {
        settingsProvider.setValue(context, id, value)
    }
}