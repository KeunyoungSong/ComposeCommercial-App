package com.keunyoung.composecommercial_app.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.keunyoung.composecommercial_app.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	@Singleton
	@Provides
	fun provideGoogleSignInOptions(@ApplicationContext context: Context): GoogleSignInClient {
		val gso = GoogleSignInOptions.Builder(
			GoogleSignInOptions.DEFAULT_SIGN_IN
		)
			.requestEmail()
			.requestIdToken(context.getString(R.string.default_web_client_id))
			.requestId()
			.requestProfile()
			.build()
		return GoogleSignIn.getClient(context, gso)
	}
}