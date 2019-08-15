package my.onotolo.andrset

import android.content.Context

abstract class Setting<T> {

    abstract val settingsProvider: SettingsProvider

    abstract val id: String

    abstract val defaultValue: T

    open operator fun get(context: Context?): T {
        return settingsProvider.getValue(context, id, defaultValue)
    }

    open operator fun set(context: Context?, value: T) {
        settingsProvider.setValue(context, id, value)
    }
}