package hr.bagy94.factorynewsreader.model

import com.google.gson.annotations.SerializedName


data class NewsListServiceResponse (
    @SerializedName("articles")
    var articles: List<Article>? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("totalResults")
    var totalResults: Long? = null)
