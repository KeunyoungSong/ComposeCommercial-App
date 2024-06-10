plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	id("kotlin-kapt")
	id("dagger.hilt.android.plugin")
}

android {
	namespace = "com.keunyoung.presentation"
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
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {
	
	implementation(project(":domain"))
	debugImplementation(libs.ui.tooling)
	kapt(libs.hilt.compiler)
	implementation(libs.hilt.android)
	implementation(libs.androidx.navigation.compose)
	implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
	implementation("com.google.accompanist:accompanist-pager:0.23.1")
	implementation("com.google.accompanist:accompanist-pager-indicators:0.23.1")
	implementation("com.google.code.gson:gson:2.11.0")
	// Firebase auth 를 활용한 구글 로그인을 위한 의존성
	implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
	implementation("com.google.firebase:firebase-analytics-ktx:22.0.1")
	implementation("com.google.firebase:firebase-auth-ktx")
	implementation("com.google.android.gms:play-services-auth:21.2.0")
	// 카카오 로그인 API 모듈
	implementation ("com.kakao.sdk:v2-user:2.20.1")
	implementation("io.coil-kt:coil-compose:2.5.0")
	
	
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}