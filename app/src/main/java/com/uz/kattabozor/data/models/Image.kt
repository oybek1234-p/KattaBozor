package com.uz.kattabozor.data.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class Image {
    @SerialName("width")
    var width = 0
    @SerialName("height")
    var height = 0
    @SerialName("url")
    var url = ""
}