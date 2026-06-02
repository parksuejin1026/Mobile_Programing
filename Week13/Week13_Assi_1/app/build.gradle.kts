plugins {
    id("com.android.application")
}

android {
    namespace = "com.cookandroid.week13_assi_1"
    compileSdk = 34 // 유지

    defaultConfig {
        applicationId = "com.cookandroid.week13_assi_1"
        minSdk = 24
        targetSdk = 34 // 유지
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // SDK 34와 완벽히 호환되는 버전으로 하향 조정
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // 명시적으로 core-ktx 버전을 SDK 34용(1.12.0)으로 고정
    implementation("androidx.core:core-ktx:1.12.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}