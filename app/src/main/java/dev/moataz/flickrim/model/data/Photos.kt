package dev.moataz.flickrim.model.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import dev.moataz.flickrim.model.data.Photo


data class Photos(
    val page: Int? = null,
val pages: String? = null,
val perpage: Int? = null,
 val total: String? = null,
var photo : List<Photo?>? = null)