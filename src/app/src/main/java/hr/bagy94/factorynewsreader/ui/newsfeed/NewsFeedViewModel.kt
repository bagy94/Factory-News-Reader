package hr.bagy94.factorynewsreader.ui.newsfeed

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import hr.bagy94.common_module.BaseViewModel
import hr.bagy94.factorynewsreader.model.Article
import hr.bagy94.factorynewsreader.repository.DatabaseProvider
import hr.bagy94.factorynewsreader.utils.DataSyncUtil
import hr.bagy94.factorynewsreader.utils.NetworkStatusCallback
import hr.bagy94.factorynewsreader.repository.NewsRepository
import hr.bagy94.factorynewsreader.repository.NewsService
import hr.bagy94.factorynewsreader.utils.ServiceProvider
import java.util.concurrent.TimeUnit

class NewsFeedViewModel (app:Application) : BaseViewModel<NewsFeedRouter> (app, NewsFeedRouter()){
    private val mRepository: NewsRepository by lazy {
        NewsRepository(
            mArticleDao = DatabaseProvider.getDatabase(app.applicationContext).articleDao(),
            mService = ServiceProvider.getService(NewsService::class.java))
    }
    private val mConnectionStatusUtil:NetworkStatusCallback = NetworkStatusCallback(app)
    private val mSyncUtil:DataSyncUtil = DataSyncUtil(app.applicationContext)
    private val mNews:MediatorLiveData<List<Article>> = MediatorLiveData()

    fun getNews():LiveData<List<Article>>{
        if(!mConnectionStatusUtil.isAvailable){
            router.showNoInternetConnectionDialog.call()
            mNews.addSource(mRepository.getLocalData()){values -> mNews.value = values}
        }
        else{
            if(isDataOld()){
                Log.d(NewsFeedViewModel::class.java.name, "Remote news")
                mNews.addSource(mRepository.getRemoteData()){ news-> run {
                        mNews.value = news
                        mRepository.saveToLocal(news)
                        mSyncUtil.onDataSynced(DB_SYNC_KEY)
                }}
            } else{
                Log.d(NewsFeedViewModel::class.java.name, "Local storage news")
                mNews.addSource(mRepository.getLocalData()){ localNews -> mNews.value = localNews}
            }
        }
        return mNews
    }

    private fun isDataOld():Boolean{
        val lastSynced = mSyncUtil.getLastSynceded(DB_SYNC_KEY)
        val current = System.currentTimeMillis()
        val duration = current - lastSynced
        return duration > SYNC_DB_PERIOD
    }

    companion object {
        private const val DB_SYNC_KEY = "database"
        private val SYNC_DB_PERIOD = TimeUnit.MINUTES.toMillis(5)
    }
}
