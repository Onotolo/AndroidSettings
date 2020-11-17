package my.onotolo.andrset

import android.content.Context

interface Setting<T> {

    val settingsProvider: SettingsProvider

    val id: String

    val defaultValue: T

    operator fun get(context: Context?): T {
        return settingsProvider.getValue(context, id, defaultValue)
    }

    operator fun set(context: Context?, value: T) {
        settingsProvider.setValue(context, id, value)
    }
}