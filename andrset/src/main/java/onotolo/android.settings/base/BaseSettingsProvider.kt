package onotolo.android.settings.base

import onotolo.android.settings.SettingsProvider


object BaseSettingsProvider : SettingsProvider() {
    override var PREFS_NAME = "Preferences"
}