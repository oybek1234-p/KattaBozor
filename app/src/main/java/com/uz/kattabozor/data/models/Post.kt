package com.uz.kattabozor.data.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class Post {
    @SerialName("id")
    var id = 0

    @SerialName("name")
    var name = ""

    @SerialName("brand")
    var brand = ""

    @SerialName("category")
    var category = ""

    @SerialName("merchant")
    var merchant = ""

    @SerialName("attributes")
    var attributes: Array<Attribute>? = null

    @SerialName("image")
    var image: Image? = null
}