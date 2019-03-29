package hr.bagy94.factorynewsreader.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import androidx.room.util.TableInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "article")
data class Article(
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("content")
    var content: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("publishedAt")
    var publishedAt: String? = null,
    @SerializedName("source")
    @Embedded
    var source: Source? = null,
    @SerializedName("title")
    @PrimaryKey
    var title: String = "",
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("urlToImage")
    var urlToImage: String? = null,
    @Ignore
    var image:ByteArray? = null): Parcelable


