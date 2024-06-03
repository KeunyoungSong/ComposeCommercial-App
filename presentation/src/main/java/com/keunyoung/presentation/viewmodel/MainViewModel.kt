package com.keunyoung.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MainViewModel: ViewModel() {
	fun openSearchForm() {
		println("오픈서치 폼")
	}
}