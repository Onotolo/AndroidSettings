package onotolo.android.settings.base

import onotolo.android.settings.Setting


abstract class BaseSetting<T> : Setting<T> {
    override val settingsProvider = BaseSettingsProvider
}