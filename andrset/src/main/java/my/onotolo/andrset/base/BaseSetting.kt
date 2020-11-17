package my.onotolo.andrset.base

import my.onotolo.andrset.Setting

abstract class BaseSetting<T>: Setting<T> {
    override val settingsProvider = BaseSettingsProvider
}