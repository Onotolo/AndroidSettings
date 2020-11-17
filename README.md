# AndroidSettings
This Android library provides you a simple way to handle application settings. It uses default Android's `SharedPreferences` API, so you can use it with your existing preferences
## Setup
Module's `build.gradle`:
```groovy
plugins {
    ...
    id 'maven'
    ...
}
...
dependencies {
    ...
    implementation 'my.onotolo.android:android-settings:0.0.4'
    ...
}
```

## Usage
* Create subclass of `SettingsProvider` with defined `PREFS_NAME` field:
```kotlin
object ApplicationSettingsProvider: SettingsProvider() {
    override val PREFS_NAME = "Your_Preferences_File_Name"
}
```
> Note that both `SettingsProvider` and `Setting` classes keep references to `context` and any other Android lifecycle's components only as it's methods parameters so you can confidently use them or their subclasses as `objects`
* Create subclass of `Setting<T>` class providing your `ApplicationSettingsProvider` as it's `settingsProvider`:
```kotlin
abstract class ApplicationSetting<T>: Setting<T> {
    override val settingsProvider = ApplicationSettingsProvider
}
```
* Create special classes for your settings. I prefer using `object`'s here:
```kotlin
object IsTimerHidden: ApplicationSetting<Boolean> {

    override val defaultValue = false
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
This library contains ready-for-use components: `BaseSettingsProvider` and `BaseSetting` which are a basic implementations of it's classes which will save your settings to a shared preferences file named `"Preferences"`

## Example
You can see example project [here](https://github.com/Onotolo/AndroidSettingsExample)
