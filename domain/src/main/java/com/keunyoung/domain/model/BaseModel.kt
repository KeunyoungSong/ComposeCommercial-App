package com.keunyoung.domain.model

sealed class BaseModel {
    abstract val type : ModelType
}

enum class ModelType {
    PRODUCT,
    BANNER,
    BANNER_LIST,
    CAROUSEL,
    RANKING,
}