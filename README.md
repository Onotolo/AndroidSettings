# AndroidSettings
This Android library provides you a simple way to handle application settings. It uses default Android's `SharedPreferences` API, so you can use it with your existing preferences
## Setup
> The followng instruction was copied from [**enefce**'s great example repository](https://github.com/enefce/AndroidLibraryForGitHubPackagesDemo/blob/master/README.md#using-a-library-from-the-github-package-registry)

> Currently the GitHub Package Registry requires us to Authenticate to download an Android Library (Public or Private) hosted on the GitHub Package Registry. This might change for future releases

### Step 1 : Generate a Personal Access Token for GitHub
- Inside you GitHub account:
	- Settings -> Developer Settings -> Personal Access Tokens -> Generate new token
	- Make sure you select the following scopes ("read:packages") and Generate a token
	- After Generating make sure to copy your new personal access token. You won’t be able to see it again!

### Step 2: Store your GitHub - Personal Access Token details
- Create a **github.properties** file within your root Android project
- In case of a public repository make sure you  add this file to .gitignore for keep the token private
	- Add properties **gpr.usr**=*GITHUB_USERID* and **gpr.key**=*PERSONAL_ACCESS_TOKEN*
	- Replace GITHUB_USERID with personal / organisation Github User ID and PERSONAL_ACCESS_TOKEN with the token generated in **#Step 1**
	
> Alternatively you can also add the **GPR_USER** and **GPR_API_KEY** values to your environment variables on you local machine or build server to avoid creating a github properties file

### Step 3 : Update build.gradle inside the application module 
- Add the following code to **build.gradle** inside the application module that will be using the library published on GitHub Packages Repository
```groovy
def githubProperties = new Properties()
githubProperties.load(new FileInputStream(rootProject.file("github.properties")))  
```
```groovy
    repositories {
        maven {
            name = "AndroidSettingsPackages"
            url = uri("https://maven.pkg.github.com/onotolo/AndroidSettings")

            credentials {
                username = githubProperties['gpr.usr'] ?: System.getenv("GPR_USER")
                password = githubProperties['gpr.key'] ?: System.getenv("GPR_API_KEY")
            }
        }
    }
```

- inside dependencies of the build.gradle of app module, use the following code
```groovy
plugins {
    ...
    id 'maven'
    ...
}
...
dependencies {
    ...
    implementation 'onotolo.android:settings:0.1.0'
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
> Note that both `SettingsProvider` and `Setting` keep references to `context` and any other Android lifecycle's components only as it's methods parameters so you can confidently use them or their subclasses as `objects`
* Implement `Setting<T>` interface providing your `ApplicationSettingsProvider` as it's `settingsProvider`:
```kotlin
abstract class ApplicationSetting<T>: Setting<T> {
    override val settingsProvider = ApplicationSettingsProvider
}
```
* Create special classes for your application's settings. You can define them as `object`'s:
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
