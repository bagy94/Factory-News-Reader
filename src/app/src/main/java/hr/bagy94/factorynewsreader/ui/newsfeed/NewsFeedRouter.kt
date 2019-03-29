package hr.bagy94.factorynewsreader.ui.newsfeed

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import hr.bagy94.common_module.BaseRouter
import hr.bagy94.common_module.SingleLiveAction

class NewsFeedRouter:BaseRouter() {
    val showNoInternetConnectionDialog:SingleLiveAction<Unit> = SingleLiveAction()

    override fun observe(lifecycleOwner: LifecycleOwner) {
        super.observe(lifecycleOwner)
        showNoInternetConnectionDialog.observe(lifecycleOwner, Observer {
            if(lifecycleOwner is NewsFeedFragment){
                lifecycleOwner.showInternetConnectionDialog()
            }
        })
    }
}