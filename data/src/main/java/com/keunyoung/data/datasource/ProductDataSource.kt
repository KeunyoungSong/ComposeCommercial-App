package com.keunyoung.data.datasource

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.keunyoung.data.deserializer.BaseModelDeserializer
import com.keunyoung.domain.model.BaseModel
import com.keunyoung.domain.model.Product
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.InputStreamReader
import javax.inject.Inject

class ProductDataSource @Inject constructor(
	@ApplicationContext private val context: Context
) {
	fun getHomeComponentList(): Flow<List<BaseModel>> = flow {
		val inputStream = context.assets.open("product_list.json")
		val inputStreamReader = InputStreamReader(inputStream)
		val jsonString = inputStreamReader.readText()
		val type = object : TypeToken<List<BaseModel>>() {}.type
		val result = GsonBuilder().registerTypeAdapter(
			BaseModel::class.java, BaseModelDeserializer()
		).create().fromJson<List<BaseModel>>(jsonString, type)
		emit(result)
	}
	
	fun getProducts(): Flow<List<Product>> = getHomeComponentList().map { it.filterIsInstance<Product>() }
	
}