![Fing](app/src/main/res/mipmap-xxhdpi/ic_launcher.png)

# Table of contents

- [Description](#Description)
- [Dependencies](#Dependencies)
- [Build](#Build)
- [Resources](#Resources)
- [Authors](#Authors)
- [License](#License)

# Description

This folder contains the sample project for an Android App containing the functionalities
provided by the [Fing SDK](https://app.fing.com/internet/business/devrecog/documentation) for network scanning and device recognition.

The Fing SDK provides the core feature of the [Fing (Network Tools)](https://play.google.com/store/apps/details?id=com.overlook.android.fing) app for Android
OS. It is available as an AAR (Android Archive) library, suitable to be used with the standard 
development tools (Android Studio) and to be published on the official Play Store. 

As a framework, it may also be used by applications written in Kotlin language.

It is compatible with Android 5.0 and above. 

The following dependencies should be added in your Gradle-based or Maven-based project.

__Fing SDK requires a license key to work. [Create a trial license](https://app.fing.com/internet/business/devrecog/trial) 
or [contact us](mailto:sales@fing.com) to get a valid key.__

# Dependencies

|       Group            | Name                    | Version
| ---------------------- | ----------------------- | --------
| androidx.appcompat     | appcompat               | 1.1.0
| com.google.android.gms | play-services-analytics | 17.0.0
| com.google.protobuf    | protobuf-java           | 2.6.1
| com.squareup.okhttp3   | okhttp                  | 4.8.0
| org.snmp4j             | snmp4j                  | 2.5.0

# Build

Android Studio automatically includes the framework in the final package. Below is an
excerpt of a Gradle build module that includes the library in the build system.

```
allprojects {
  repositories {
    jcenter()
    google()
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
    implementation(group: 'com.github.fingltd', name: 'devrecog', version:'2.5.2', ext: 'aar')
    implementation(group: 'androidx.appcompat', name: 'appcompat', version:'1.1.0')
    implementation(group: 'androidx.coordinatorlayout', name: 'coordinatorlayout', version:'1.1.0')
    implementation(group: 'com.google.android.material', name: 'material', version:'1.1.0')
    implementation(group: 'com.google.android.gms', name: 'play-services-analytics', version:'17.0.0')
    implementation(group: 'com.google.protobuf', name: 'protobuf-java', version:'2.6.1')
    implementation(group: 'com.squareup.okhttp3', name: 'okhttp', version:'4.8.0')
    implementation(group: 'org.snmp4j', name: 'snmp4j', version:'2.5.0')
}
```

# Resources

## Current Version

|           | Version | Documentation
| --------- | ------- | -------------
| Fing SDK  | [![](https://jitpack.io/v/fingltd/devrecog.svg)](https://jitpack.io/#fingltd/devrecog) | [Doc](https://github.com/fingltd/devrecog/wiki/Fing-Android-SDK)

# Authors

**Project Owner**

- Marco De Angelis (marco at fing.com)

**Contributors**

- Daniele Pantaleone (daniele at fing.com)
- Tommaso Latini (tommaso at fing.com)

# License

Code released under the [MIT License](https://github.com/fingltd/devrecog-sample-app-android/blob/master/LICENSE).