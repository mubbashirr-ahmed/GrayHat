package com.assesment.grayhat.data.Model

data class Items(
    val imgURL: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val category: String? = null,
    val price: Double? = null
){
    constructor() : this("", 0, "", "",0.0)
}
