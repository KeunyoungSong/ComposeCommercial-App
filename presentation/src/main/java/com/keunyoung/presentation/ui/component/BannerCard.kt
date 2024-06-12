package com.keunyoung.presentation.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.keunyoung.presentation.model.BannerVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BannerCard(presentationVM: BannerVM) {
	Card(shape = RoundedCornerShape(8.dp),
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp),
		onClick = { presentationVM.openBanner(presentationVM.model.bannerId) }) {
		AsyncImage(
			model = presentationVM.model.imageUrl,
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.fillMaxWidth()
				.aspectRatio(2f)
		)
	}
}