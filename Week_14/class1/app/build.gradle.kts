plugins {
    id("com.android.application")
}

android {
    namespace = "com.cookandroid.class1"
    compileSdk = 34 // AGP 8.2.0 환경에 맞는 안정적인 SDK 34 고정

    defaultConfig {
        applicationId = "com.cookandroid.class1"
        minSdk = 24
        targetSdk = 34 // SDK 34 고정
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // [핵심] 라이브러리가 1.16.0을 강제 호출하지 못하도록 1.13.0 버전으로 완벽히 수동 고정합니다.
    implementation("androidx.core:core:1.13.0")
    implementation("androidx.core:core-ktx:1.13.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}