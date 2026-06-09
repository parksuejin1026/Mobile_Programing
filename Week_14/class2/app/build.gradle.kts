plugins {
    id("com.android.application")
}

android {
    namespace = "com.cookandroid.class2"
    compileSdk = 34 // 가장 안정적인 SDK 34로 설정

    defaultConfig {
        applicationId = "com.cookandroid.class1"
        minSdk = 24
        targetSdk = 34 // targetSdk도 34로 일치
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
    // [핵심] 다른 라이브러리가 최신 버전을 멋대로 부르지 못하게 1.13.0으로 강제 고정
    implementation("androidx.core:core:1.13.0") {
        version { strictly("1.13.0") }
    }
    implementation("androidx.core:core-ktx:1.13.0") {
        version { strictly("1.13.0") }
    }

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}