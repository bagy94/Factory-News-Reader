package hr.bagy94.factorynewsreader.repository

import hr.bagy94.factorynewsreader.model.NewsListServiceResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {
    @GET("v1/articles?source=bbc-news&sortBy=top&apiKey=87f8561cf978440a909410e218dc3383")
    fun getTopBBCHeadlines(): Call<NewsListServiceResponse>
}
