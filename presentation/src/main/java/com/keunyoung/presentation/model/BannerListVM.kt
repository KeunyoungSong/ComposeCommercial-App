package com.keunyoung.presentation.model

import com.keunyoung.domain.model.BannerList
import com.keunyoung.presentation.delegate.BannerDelegate

class BannerListVM(model: BannerList, private val bannerDelegate: BannerDelegate) : PresentationVM(model),
	BannerDelegate by bannerDelegate {
	fun openBannerList(bannerId: String) {
		bannerDelegate.openBanner(bannerId)
	}
}