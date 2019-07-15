package my.onotolo.andrset

import android.content.Context

abstract class ArrayItemSetting<T>: Setting<T>() {

    abstract val array: Array<T>

    override fun get(context: Context?): T {
        val index =
            settingsProvider.getValue(
                context,
                id,
                array.indexOf(defaultValue)
            )
        return array[index]
    }

    override fun set(context: Context?, value: T) {
        settingsProvider.setValue(
            context,
            id,
            array.indexOf(value))
    }
}