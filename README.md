![Fing](app/src/main/res/mipmap-xxhdpi/ic_launcher.png)

Table of contents
-----------------

- [Description](#Description)
- [Dependencies](#Dependencies)
- [Build](#Build)
- [Resources](#Resources)
- [Authors](#Authors)
- [License](#License)

Description
------------
This folder contains the sample project for an Android App containing the functionalities
provided by the [Fing SDK](https://app.fing.com/internet/business/devrecog/documentation) for network scanning and device recognition.

The Fing SDK provides the core feature of the [Fing (Network Tools)](https://play.google.com/store/apps/details?id=com.overlook.android.fing) app for Android
OS. It is available as an AAR (Android Archive) library, suitable to be used with the standard 
development tools (Android Studio) and to be published on the official Play Store. 
As a framework, it may also be used by applications written in Kotlin language. It is
compatible with Android 5.0 and above. 

The following dependencies should be added in your Gradle-based or Maven-based project.

__Fing SDK requires a license key to work. [Create a trial license](https://app.fing.com/internet/business/devrecog/trial) 
or [contact us](mailto:sales@fing.com) to get a valid key.__

Dependencies
-----
|       Group            | Name                    | Version
| ---------------------- | ----------------------- | --------
| androidx.appcompat     | appcompat               | 1.1.0
| com.google.android.gms | play-services-analytics | 17.0.0
| com.google.protobuf    | protobuf-java           | 2.6.1
| org.snmp4j             | snmp4j                  | 2.5.0 
| com.squareup.okhttp3   | okhttp                  | 4.2.0

The archive fing-kit.aar should be placed locally in a folder placed at the same level
of the Android app source code, (e.g. if your source code is in <root/app/src>, place
the library in <root/app/libs>) and it will be added as transitive compilation item in
your build system.

Build
-----

Android Studio automatically includes the framework in the final package. Below is an
excerpt of a Gradle build module that includes the library in the build system.

```
allprojects {
  repositories {
    jcenter()
    flatDir {
      dirs 'libs'
    }
    google()
  }
}

dependencies {
  compile(name:'fing-kit', ext:'aar') {
    transitive=true
  }
  implementation 'androidx.appcompat:appcompat:1.1.0'
  implementation 'com.google.android.gms:play-services-analytics:17.0.0'
  //noinspection GradleDependency
  implementation 'com.google.protobuf:protobuf-java:2.6.1'
  Implementation 'com.squareup.okhttp3:okhttp:4.2.0'
  implementation 'org.snmp4j:snmp4j:2.5.0'
}
```

Resources
---------------

### Current Version

|           | Version |
| --------- | ------- |
| Fing SDK  | 2.5.0   |

### Latest Doc

[Fing Mobile SDK]("https://get.fing.com/fing-business/devrecog/documentation/Fing_Mobile_SDK.pdf")

Authors
--------

**Project Owner**

- Marco De Angelis (marco at fing.com)

**Contributors**

- Daniele Pantaleone (daniele at fing.com)
- Tommaso Latini (tommaso at fing.com)

License
-------

Code released under the [MIT License](https://github.com/fingltd/devrecog-sample-app-android/blob/master/LICENSE).