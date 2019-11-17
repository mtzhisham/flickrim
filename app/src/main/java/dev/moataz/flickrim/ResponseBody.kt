package dev.moataz.flickrim

import com.google.gson.annotations.SerializedName


data class ResponseBody(@SerializedName("photos") val photos: Photos? = null,
                        @SerializedName("stat") val stat: String? = null )