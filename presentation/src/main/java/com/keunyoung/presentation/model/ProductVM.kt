package com.keunyoung.presentation.model

import com.keunyoung.domain.model.Product
import com.keunyoung.presentation.delegate.ProductDelegate

class ProductVM(model: Product, productDelegate: ProductDelegate) : PresentationVM<Product>(model),
	ProductDelegate by productDelegate