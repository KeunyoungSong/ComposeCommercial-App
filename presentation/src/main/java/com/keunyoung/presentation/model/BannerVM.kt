package com.keunyoung.presentation.model

import com.keunyoung.domain.model.Banner
import com.keunyoung.presentation.delegate.BannerDelegate

class BannerVM(model: Banner, bannerDelegate: BannerDelegate) : PresentationVM<Banner>(model),
	BannerDelegate by bannerDelegate {
	
}