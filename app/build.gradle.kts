plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	id("kotlin-kapt")
	id("dagger.hilt.android.plugin")
	id("com.google.gms.google-services")
	id("com.google.firebase.crashlytics")
}

android {
	signingConfigs {
		create("release") {
			storeFile = file("/Users/brandon/Documents/keys/android-release/ComposeCommercial.jks.kts")
			storePassword = "123456"
			keyAlias = "test-key"
			keyPassword = "123456"
		}
	}
	namespace = "com.keunyoung.composecommercial_app"
	compileSdk = 34
	
	defaultConfig {
		applicationId = "com.keunyoung.composecommercial_app"
		minSdk = 28
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}
	
	buildTypes {
		release {
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
			signingConfig = signingConfigs.getByName("release")
		}
		debug {
			isDebuggable = true
			applicationIdSuffix = ".dev"
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
	implementation(project(":data"))
	implementation(project(":presentation"))
	
	implementation(libs.hilt.android)
	implementation(libs.androidx.room.ktx)
	
	kapt(libs.hilt.compiler)
	// 구글 로그인을 위한 의존성
	implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
	implementation("com.google.firebase:firebase-analytics:22.0.1")
	implementation("com.google.firebase:firebase-auth")
	implementation("com.google.android.gms:play-services-auth:21.2.0")
	// 카카오 로그인을 위한 의존성
	implementation ("com.kakao.sdk:v2-user:2.20.1")
	// Crashlytics
	implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
	
	// Add the dependencies for the Crashlytics and Analytics libraries
	// When using the BoM, you don't specify versions in Firebase library dependencies
	implementation("com.google.firebase:firebase-crashlytics")
	implementation("com.google.firebase:firebase-analytics")
	
	implementation("com.google.android.gms:play-services-ads:23.1.0")
	
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
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}