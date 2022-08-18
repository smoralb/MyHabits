# BaseApplication
Base application to use and create multiple apps!.


##TESTING

For testing I have used JUnit5 for Unit testing and Jacoco for coe coverage report.01

All the dependencies are added in the core `build.gradle` file

```
dependencies {
 ...
 api testing.values()
}
```

That are defined in the `dependencies.gradle` file

```
    testing = [
            jupiter_api          : "org.junit.jupiter:junit-jupiter-api:${versions.jupiter_version}",
            jupiter_engine       : "org.junit.jupiter:junit-jupiter-engine:${versions.jupiter_version}",
            jupiter_parameterized: "org.junit.jupiter:junit-jupiter-params:${versions.jupiter_vintage}",
            jupiter_vintage      : "org.junit.vintage:junit-vintage-engine:${versions.jupiter_version}",
            mockito_kotlin       : "org.mockito.kotlin:mockito-kotlin:${versions.mockito_version}",
            mockito_jupiter      : "org.mockito:mockito-junit-jupiter:${versions.mockito_jupiter}",
            mockito_core         : "org.mockito:mockito-core:${versions.mockito_core}",
            mockito_inline       : "org.mockito:mockito-inline:${versions.mockito_inline}",
            coroutines_test      : "org.jetbrains.kotlinx:kotlinx-coroutines-test:${versions.coroutines_test_version}",
            core_ktx_testing     : "androidx.arch.core:core-testing:${versions.core_testing}",
    ]
```

All the Unit tests are collected under the `test` folder.

Once we have all the Unit tests created and working. We are able to start reporting the code coverage.01

For that, I have applied the Jacoco plugin in the `jacoco.gradle` file.

- To see tests results -> build >> tests >> index.html

- To see Jacoco reports -> build >>reports >> testDebugUnitTestCoverage >> html >> index.html


##APP STRUCTURE

The app has different modules that will be increased. At this moment exists common modules that all feature modules will depend.

- core -> Has all the common code that will be used for the entire app. Included app, navigator and all the features modules.

- app -> Is just the application module, has no logic.

- navigator -> Contains the implementation for the navigation of each feature.

- resources -> Core has a dependency of this module. That module contains all the common resources for the entire app. The resources that are specific for a module, will be declared in the res folder of this module.

- ft_xxx -> The feature module contains the implementation of the feature that will be included in the app (ft_landing, ft_home, etc).


![img.png](app_structure.JPG)


##APP SECURITY

To securely store all the user information, I have chosen [EncryptedSharedPreferences](https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences). This is an **androidx** security library that enables to store encrypted data in shared preferences.

The implementation is very straightforward:

Add the androidx security dependency

```androidx.security:security-crypto:1.0.0```

Create an instance of SharedPreferences that will encrypt all user data
```
EncryptedSharedPreferences.create(
    SHARED_PREFERENCES,
    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
    context,
    AES256_SIV,
    AES256_GCM
)
```

And that's all!

To read and write data from this EncryptedSharedPreferences will be the same as using SharedPreferences

**Write data:**

```
with(getSharedPreferences(requireContext()).edit()) {
    putString(USER_NAME, userName)
    putString(USER_PASSWORD, password)
    putBoolean(USER_REMEMBERED, true)
    apply()
}
```

**Read data:**

```
fun Fragment.getUserEmail() =
    getSharedPreferences(requireContext()).getString(USER_NAME, EMPTY_STRING)

fun Fragment.getUserPassword() =
    getSharedPreferences(requireContext()).getString(USER_PASSWORD, EMPTY_STRING)

fun Fragment.isUserRemembered() =
    getSharedPreferences(requireContext()).getBoolean(USER_REMEMBERED, false)
```

**Clear data:**

```
fun Fragment.clearUserdata() =
    getSharedPreferences(requireContext()).edit()
        .apply {
            remove(USER_NAME)
            remove(USER_PASSWORD)
            remove(USER_REMEMBERED)
        }.apply()
```

This method may be replaced by **JetPack DataStore** (after further investigation).

## PUSH NOTIFICATIONS

In order to receive notifications as a reminder, the app is going to use the FCM (Firebase Cloud Messaging). The steps to add the library will be:

1. Add FCM dependency. As I am using the Firebase Boom, I don't have to specify the version, so it will be something like this

``` firebase_messaging: 'com.google.firebase:firebase-messaging' ```

2. Create a receiver that is going to inherit the FirebaseMessagingService class and override the onMessageReceived method. This method is
triggered when the app receive a notification.

```
class FirebaseReceiver: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // All the logic to create and show the notification push
    }
}
```

The onMessageReceived is necessary if you want to retrieve some data that comes from Firebase or any other remote entity.method

3. Add the receiver to the app manifest.

```
<service android:name=".FirebaseMessageReceiver">
			<intent-filter>
				<action android:name="com.google.firebase.MESSAGING_EVENT" />
			</intent-filter>
</service>
```

4. Also add the INTERNET permission

```<uses-permission android:name=”android.permission.INTERNET” />```

5. Google brings the methods to create an standard push notification but there is also the possibility to create a push notification with a custom layout. for this case, I am going to use
a custom layout.

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
	xmlns:android="http://schemas.android.com/apk/res/android"
	android:id="@+id/linear_layout"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	android:orientation="horizontal"
	android:padding="20dp">

	<!-- Parent Layout of ImageView -->
	<LinearLayout
		android:layout_width="wrap_content"
		android:layout_height="wrap_content">

		<!--Image to be displayed beside the notification text-->
		<ImageView
			android:id="@+id/icon"
			android:layout_width="50dp"
			android:layout_height="50dp"
			android:padding="5dp"
			android:src="@drawable/gfg" />
	</LinearLayout>

	<!-- Parent layout for holding the Title and the Body-->
	<LinearLayout
		android:layout_width="0dp"
		android:layout_height="wrap_content"
		android:layout_weight="1"
		android:orientation="vertical"
		android:padding="5dp">

		<!-- TextView for Title -->
		<TextView
			android:id="@+id/title"
			android:layout_width="match_parent"
			android:layout_height="wrap_content"
			android:text="Title"
			android:textColor="#000"
			android:textStyle="bold" />

		<!-- TextView for Body -->
		<TextView
			android:id="@+id/message"
			android:layout_width="match_parent"
			android:layout_height="wrap_content"
			android:text="Message"
			android:textSize="15sp" />

	</LinearLayout>

</LinearLayout>
```

**This is just a sample**




##FUTURE WORK
    - Add Sonar/Codecov and Bitrise
    - Improve documentation
    - Migrate to JetPack Compose
    - Migrate to Flow
    - Add localization for strings
    - Use Android 12 Splash Screen instead of using themes
    - Investigate and implement JetPack DataStore to store user authentication data.

## FEATURES
    - Add Biometric to login
    - Add push notification
    - Add reminder

## BUGS / REFACTOR
    - Check if there are unused dependencies
    - Review all TextView styles and unify
    - Show buttons above the keyboard (e.g add task)
    - Don't let user to delete another task while one is already removing.
  
References:

- ListAdapter -> https://proandroiddev.com/android-data-binding-listadapter-9e72ce50e8c7
- Testing LiveData in JUnit 4 & JUnit 5 -> https://jeroenmols.com/blog/2019/01/17/livedatajunit5/
- ViewModelScope -> https://medium.com/androiddevelopers/easy-coroutines-in-android-viewmodelscope-25bffb605471
- EncryptedSharedPreferences -> https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences && https://proandroiddev.com/encrypted-preferences-in-android-af57a89af7c8