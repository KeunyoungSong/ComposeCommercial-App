package com.keunyoung.presentation.ui.main

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.keunyoung.domain.model.AccountInfo
import com.keunyoung.presentation.viewmodel.MainViewModel

@Composable
fun MyPageScreen(viewModel: MainViewModel, googleSignInClient: GoogleSignInClient, navHostController: NavHostController) {
	val accountInfo by viewModel.accountInfo.collectAsState()
	val firebaseAuth by lazy { FirebaseAuth.getInstance() }
	val context = LocalContext.current
	val startForResult =
		rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
			if (result.resultCode == Activity.RESULT_OK) {
				val indent = result.data
				if (indent != null) {
					val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(indent)
					handleSignInResult(context, task, viewModel, firebaseAuth)
				}
			}
		}
	val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
		when {
			error != null -> {
				Log.e("Kakao", "카카오 계정 로그인 실패", error)
			}
			token != null -> {
				loginWithKakaoNickName(token, viewModel)
			}
		}
	}
	
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(30.dp)
	) {
		if (accountInfo != null) {
			Image(
				painter = rememberAsyncImagePainter(accountInfo?.profileImageUrl),
				contentDescription = null,
				modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.padding(5.dp)
					.size(100.dp)
					.clip(CircleShape),
				contentScale = ContentScale.Crop
			)
			Spacer(modifier = Modifier.height(20.dp))
			Text(
				text = "${accountInfo?.name}",
				textAlign = TextAlign.Center,
				modifier = Modifier.fillMaxWidth()
			)
			Spacer(modifier = Modifier.height(50.dp))
			Button(onClick = {viewModel.openPurchaseHistory(navHostController = navHostController)}, modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp)) {
				Text("결제내역 보기", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
				Icon(Icons.Filled.ArrowForward, contentDescription = null)
			}
			Spacer(modifier = Modifier.weight(1f))
			Button(onClick = {
				viewModel.signOut()
				when (accountInfo?.type) {
					AccountInfo.Type.KAKAO -> UserApiClient.instance.logout { }
					AccountInfo.Type.GOOGLE -> firebaseAuth.signOut()
					else -> Unit
				}
			}, modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp)) {
				Text("로그아웃")
			}
			Spacer(modifier = Modifier.height(70.dp))
		} else {
			Button(
				onClick = { startForResult.launch(googleSignInClient.signInIntent) },
				modifier = Modifier.fillMaxWidth()
			) {
				Text("구글 로그인")
			}
			Button(onClick = { loginKakao(context, kakaoCallback) }, modifier = Modifier.fillMaxWidth()) {
				Text("카카오 로그인")
			}
		}
	}
}

private fun loginKakao(context: Context, kakaoCallback: (OAuthToken?, Throwable?) -> Unit) {
	if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
		UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
			if (error != null) {
				Log.e("Kakao", "카카오톡 로그인 실패", error)
			}
			if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
				return@loginWithKakaoTalk
			}
			UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoCallback)
		}
	} else {
		UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoCallback)
	}
}

private fun loginWithKakaoNickName(token: OAuthToken, viewModel: MainViewModel) {
	UserApiClient.instance.me { user, error ->
		when {
			error != null -> {
				Log.e("Kakao", "닉네임으로 로그인하기 실패", error)
			}
			user != null -> {
				viewModel.signIn(
					AccountInfo(
						tokenId = token.idToken.orEmpty(),
						name = user.kakaoAccount?.profile?.nickname.orEmpty(),
						type = AccountInfo.Type.KAKAO,
						profileImageUrl = user.kakaoAccount?.profile?.thumbnailImageUrl.orEmpty(),
					)
				)
			}
		}
	}
}

private fun handleSignInResult(
	context: Context,
	accountTask: Task<GoogleSignInAccount>,
	viewModel: MainViewModel,
	firebaseAuth: FirebaseAuth
) {
	try {
		val account = accountTask.result ?: return
		val credential = GoogleAuthProvider.getCredential(account.idToken, null)
		firebaseAuth.signInWithCredential(credential).addOnCompleteListener(context as Activity) { task ->
			if (task.isSuccessful) {
				viewModel.signIn(
					AccountInfo(
						tokenId = account.idToken.orEmpty(),
						name = account.displayName.orEmpty(),
						type = AccountInfo.Type.GOOGLE,
						profileImageUrl = account.photoUrl.toString()
					)
				)
			} else {
				viewModel.signOut()
				firebaseAuth.signOut()
			}
		}
	} catch (e: Exception) {
		e.printStackTrace()
	}
}