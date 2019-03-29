package hr.bagy94.factorynewsreader.ui.newsdetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hr.bagy94.common_module.BaseRouter
import hr.bagy94.common_module.BaseViewModel
import hr.bagy94.factorynewsreader.model.Article
import hr.bagy94.factorynewsreader.repository.DatabaseProvider
import hr.bagy94.factorynewsreader.repository.NewsRepository
import hr.bagy94.factorynewsreader.repository.NewsService
import hr.bagy94.factorynewsreader.utils.ServiceProvider

class NewsSliderViewModel(app:Application): BaseViewModel<BaseRouter>(app,BaseRouter()) {

    private val mSelectedArticle:MutableLiveData<Int> = MutableLiveData(0)



    private val mRepository: NewsRepository by lazy {
        NewsRepository(
            mArticleDao = DatabaseProvider.getDatabase(app.applicationContext).articleDao(),
            mService = ServiceProvider.getService(NewsService::class.java))
    }

    val mNews:LiveData<List<Article>> get() = mRepository.getLocalData()


}
