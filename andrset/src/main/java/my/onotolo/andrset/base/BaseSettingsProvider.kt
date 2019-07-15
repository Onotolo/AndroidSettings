package my.onotolo.andrset.base

import my.onotolo.andrset.SettingsProvider

object BaseSettingsProvider: SettingsProvider() {
    override var PREFS_NAME = "Preferences"
}