package com.keunyoung.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.keunyoung.presentation.ui.theme.ComposeCommercialAppTheme
import com.keunyoung.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	
	private val viewModel: MainViewModel by viewModels()
	
	@Inject
	lateinit var googleSignInClient: GoogleSignInClient
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ComposeCommercialAppTheme { // A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
					MainNavigationScreen(googleSignInClient = googleSignInClient)
				}
			}
		}
		viewModel.updateColumnCount(getColumnCount())
	}
	
	private fun getColumnCount(): Int {
		return getDisplayWithDp().toInt() / DEFAULT_COLUMN_SIZE
	}
	
	private fun getDisplayWithDp(): Float {
		return resources.displayMetrics.run { widthPixels / density }
	}
	
	companion object {
		private const val DEFAULT_COLUMN_SIZE = 160
	}
}

