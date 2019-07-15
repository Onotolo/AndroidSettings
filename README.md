# AndroidSettings
This Android library provides you a simple way to handle application settings. It uses default Android's `SharedPreferences` API, so you can use it with your existing preferences.
## Setup
Project's `build.gradle`:
```groovy
allprojects {
    repositories {
        ...
        maven {
            url = "https://raw.githubusercontent.com/Onotolo/AndroidLibsMavenRepo/master"
        }
    }
}
```

Module's `build.gradle`:
```groovy
dependencies {
    ...
    implementation 'my.onotolo.android:android-settings:0.0.2'
}
```

## Usage
* Create sub class of `SettingsProvider` with defined `PREFS_NAME` field:
```kotlin
object ApplicationSettingsProvider: SettingsProvider() {
    override val PREFS_NAME = "Your_Preferences_File_Name"
}
```
> Note that both `SettingsProvider` and `Setting` classes keep references to `context` and any other Android lifecycle's components only as it's methods parameters so you can confidently use them or their subclasses as `objects`.
* Create sub class of `Setting<T>` class providing your `ApplicationSettingsProvider` as it's `settingsProvider`:
```kotlin
abstract class ApplicationSetting<T>: Setting<T>() {
    override val settingsProvider = ApplicationSettingsProvider
}
```
* Create special classes for your settings. I prefer using `object`'s here:
```kotlin
object IsTimerHidden: ApplicationSetting<Boolean>() {

    override val defaultValue = false

    override val settingNameResId = R.string.set_hide_timer
    override val descriptionResId = R.string.set_hide_timer_descr
    override val id: String = "Hide timer"
}
```
Now you can use your setting object like this:
* Get setting value
```kotlin
{
    ...
    if (IsTimerHidden[context])
        ...
    ...
}
```
* Set setting value
```kotlin
{
    ...
    IsTimerHidden[context] = false
    ...
}
```
## Base Implementation
This library contains ready-for-use components: `BaseSettingsProvider` and `BaseSetting`, which are a basic implementations of it's classes, which will save your settings to a shared preferences file named `"Preferences"`.
