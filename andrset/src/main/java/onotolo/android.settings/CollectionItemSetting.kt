package onotolo.android.settings

import android.content.Context

abstract class CollectionItemSetting<T>: Setting<T> {

    abstract val array: Collection<T>

    override fun get(context: Context?): T {
        val index =
            settingsProvider.getValue(
                context,
                id,
                array.indexOf(defaultValue)
            )
        return array.elementAt(index)
    }

    override fun set(context: Context?, value: T) {
        settingsProvider.setValue(
            context,
            id,
            array.indexOf(value))
    }
}