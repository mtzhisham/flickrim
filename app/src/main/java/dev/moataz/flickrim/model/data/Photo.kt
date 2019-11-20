package dev.moataz.flickrim.model.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photo")
data class Photo(

    @NonNull
    @ColumnInfo(name = "_id", index = true)
    @PrimaryKey(autoGenerate = true)
    @SerializedName( "_id")
    val _id: Long =0,

    @ColumnInfo(name = "id")
    @SerializedName( "id") var id: String,

    @ColumnInfo(name = "owner")
                 @SerializedName("owner") var owner: String? = null,
    @ColumnInfo(name = "secret")
                 @SerializedName("secret") var secret: String? = null,
    @ColumnInfo(name = "server")
                 @SerializedName("server") var server: String? = null,
    @ColumnInfo(name = "farm")
                 @SerializedName("farm") var farm : Int? = null,
    @ColumnInfo(name = "title")
                 @SerializedName("title") var title: String? = null,
    @ColumnInfo(name = "ispublic")
                 @SerializedName("ispublic") var ispublic: Int? = null,
    @ColumnInfo(name = "isfriend")
                 @SerializedName("isfriend") var isfriend: Int? = null,
    @ColumnInfo(name = "isfamily")
                 @SerializedName( "isfamily") var isfamily: Int? = null,
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
                 var image: ByteArray? = null

){

    public fun getImageUrl() :String = "https://farm${farm}" +
            ".staticflickr.com/${server}/${id}_${secret}_b.jpg"


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Photo

        if (_id != other._id) return false
        if (id != other.id) return false
        if (owner != other.owner) return false
        if (secret != other.secret) return false
        if (server != other.server) return false
        if (farm != other.farm) return false
        if (title != other.title) return false
        if (ispublic != other.ispublic) return false
        if (isfriend != other.isfriend) return false
        if (isfamily != other.isfamily) return false
        if (image != null) {
            if (other.image == null) return false
            if (!image!!.contentEquals(other.image!!)) return false
        } else if (other.image != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + (owner?.hashCode() ?: 0)
        result = 31 * result + (secret?.hashCode() ?: 0)
        result = 31 * result + (server?.hashCode() ?: 0)
        result = 31 * result + (farm ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (ispublic ?: 0)
        result = 31 * result + (isfriend ?: 0)
        result = 31 * result + (isfamily ?: 0)
        result = 31 * result + (image?.contentHashCode() ?: 0)
        return result
    }


}