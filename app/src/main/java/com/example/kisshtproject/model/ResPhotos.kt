package com.example.kisshtproject.model

import com.google.gson.annotations.SerializedName

class ResPhotos {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("urls")
    var urls: PhotosUrls? = null
}