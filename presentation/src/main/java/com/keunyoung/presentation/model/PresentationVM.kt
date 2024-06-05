package com.keunyoung.presentation.model

import com.keunyoung.domain.model.BaseModel

sealed class PresentationVM<T: BaseModel>(val model: T) {

}