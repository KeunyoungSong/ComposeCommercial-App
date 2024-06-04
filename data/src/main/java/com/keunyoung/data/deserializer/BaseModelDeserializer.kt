package com.keunyoung.data.deserializer

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.keunyoung.domain.model.Banner
import com.keunyoung.domain.model.BannerList
import com.keunyoung.domain.model.BaseModel
import com.keunyoung.domain.model.Carousel
import com.keunyoung.domain.model.ModelType
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.Ranking
import java.lang.reflect.Type

class BaseModelDeserializer : JsonDeserializer<BaseModel> {
	
	companion object {
		private const val TYPE = "type"
		private val gson: Gson = GsonBuilder().create()
	}
	
	override fun deserialize(
		json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?
	): BaseModel {
		val root = json?.asJsonObject
		val typeString =
			root?.get(TYPE)?.asString ?: throw IllegalArgumentException("Type is missing")
		val type = ModelType.valueOf(typeString)
		
		return when (type) {
			ModelType.PRODUCT -> gson.fromJson(root, Product::class.java)
			ModelType.BANNER -> gson.fromJson(root, Banner::class.java)
			ModelType.BANNER_LIST -> gson.fromJson(root, BannerList::class.java)
			ModelType.CAROUSEL -> gson.fromJson(root, Carousel::class.java)
			ModelType.RANKING -> gson.fromJson(root, Ranking::class.java)
		}
	}
}