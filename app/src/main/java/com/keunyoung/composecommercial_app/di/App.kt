package com.keunyoung.composecommercial_app.di

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
	override fun onCreate() {
		super.onCreate()
		// TODO 이후 키 적용 방식 변경
		KakaoSdk.init(this, "022a07c1302ea712dc8d469b75938369")
		}
}