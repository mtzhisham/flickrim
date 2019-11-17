package dev.moataz.flickrim

import com.google.gson.annotations.SerializedName


data class Photos(@SerializedName("page") val page: Int? = null,
                  @SerializedName("pages") val pages: String? = null,
                  @SerializedName( "perpage") val perpage: Int? = null,
                  @SerializedName( "total") val total: String? = null,
                  @SerializedName( "photo") val photo : List<Photo?>? = null
                  )