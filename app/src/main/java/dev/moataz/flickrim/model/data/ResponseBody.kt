package dev.moataz.flickrim.model.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import dev.moataz.flickrim.model.data.Photos


data class ResponseBody(
    val photos: Photos? = null,
    val stat: String? = null )