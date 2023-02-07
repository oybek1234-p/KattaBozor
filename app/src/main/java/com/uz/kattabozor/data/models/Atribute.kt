package com.uz.kattabozor.data.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class Attribute {
    @SerialName("value")
    var value = ""
    @SerialName("name")
    var name = ""
}