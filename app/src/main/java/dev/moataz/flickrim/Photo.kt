package dev.moataz.flickrim

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Photo(@SerializedName( "id") val id: String? = null,
                 @SerializedName("owner") val owner: String? = null,
                 @SerializedName("secret") val secret: String? = null,
                 @SerializedName("server") val server: String? = null,
                 @SerializedName("farm") val farm : Int? = null,
                 @SerializedName("title") val title: String? = null,
                 @SerializedName("ispublic") val ispublic: Int? = null,
                 @SerializedName("isfriend") val isfriend: Int? = null,
                 @SerializedName( "isfamily") val isfamily: Int? = null


)