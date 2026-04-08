plugins {
    alias(libs.plugins.android.application)
<<<<<<< HEAD
    id("com.google.gms.google-services")
=======
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
}

android {
    namespace = "comp3025.assignment2"
<<<<<<< HEAD
    compileSdk = 35
=======
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9

    defaultConfig {
        applicationId = "comp3025.assignment2"
        minSdk = 33
<<<<<<< HEAD
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
=======
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
<<<<<<< HEAD
        dataBinding = true
=======
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
<<<<<<< HEAD
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
=======
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.okhttp)
<<<<<<< HEAD

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
}
=======
}
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
