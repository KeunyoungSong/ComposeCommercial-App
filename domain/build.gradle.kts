
plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	id("kotlin-kapt")
	id("dagger.hilt.android.plugin")
	id("kotlin-parcelize")
}

android {
	namespace = "com.keunyoung.domain"
	compileSdk = 34
	
	defaultConfig {
		minSdk = 28
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
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
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	
	implementation(libs.hilt.android)
	kapt(libs.hilt.compiler)
	
	// 테스팅을 위한 의존성 추가
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
	testImplementation("app.cash.turbine:turbine:0.12.1")
	testImplementation("com.google.truth:truth:1.1.3")
	testImplementation("org.mockito:mockito-core:5.0.0")
	testImplementation("org.mockito:mockito-inline:5.0.0")
	
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}