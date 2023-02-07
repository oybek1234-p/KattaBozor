package com.uz.kattabozor.data.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class PostResponse {
    @SerialName("offers")
    var posts: Array<Post>? = null
}