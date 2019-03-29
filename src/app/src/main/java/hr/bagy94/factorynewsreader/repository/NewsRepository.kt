package hr.bagy94.factorynewsreader.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hr.bagy94.factorynewsreader.model.Article
import hr.bagy94.factorynewsreader.model.NewsListServiceResponse
import hr.bagy94.common_basic.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(private val mArticleDao:ArticleDao,private val mService: NewsService){

    fun getRemoteData():LiveData<List<Article>>{
        val mData = MutableLiveData<List<Article>>()
        mService.getTopBBCHeadlines().enqueue(object : Callback<NewsListServiceResponse>{
            override fun onFailure(call: Call<NewsListServiceResponse>, t: Throwable) {
                Log.d(NewsRepository::class.java.name,"failure-${t.message}")
            }
            override fun onResponse(call: Call<NewsListServiceResponse>, response: Response<NewsListServiceResponse>) {
                response.body()?.articles?.let {
                    mData.value = it
                }
            }
        })
        return mData
    }

    fun getLocalData():LiveData<List<Article>>{
        return mArticleDao.getAll()
    }

    fun saveToLocal(news: List<Article>){
        async {
            mArticleDao.deleteAll()
            mArticleDao.insertAll(news)
        }
        Log.d(NewsRepository::class.java.name, "Local storage saved")
    }

}