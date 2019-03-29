package hr.bagy94.factorynewsreader.ui.newsdetail


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.ui.NavigationUI
import hr.bagy94.common_module.BaseFragment
import hr.bagy94.factorynewsreader.MainActivity
import hr.bagy94.factorynewsreader.R
import hr.bagy94.factorynewsreader.adapter.NewsSliderFragmentPageAdapter
import hr.bagy94.factorynewsreader.model.Article
import hr.bagy94.factorynewsreader.utils.setBackButtonInToolbarVisible


class NewsSliderFragment : BaseFragment<NewsSliderViewModel,hr.bagy94.factorynewsreader.databinding.FragmentNewsSliderBinding>() {
    override val provideViewId: Int
        get() = R.layout.fragment_news_slider
    override val provideViewModelClass: Class<NewsSliderViewModel>
        get() = NewsSliderViewModel::class.java

    private var mSelectedArticle:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is MainActivity){
            (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(NewsSliderFragment::class.java.name, "Item selected ")
        if(item.itemId ==android.R.id.home ){
            Log.d(NewsSliderFragment::class.java.name, "Item selected back")
            back()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onParseArguments(arguments: Bundle) {
        if(arguments.containsKey("selected")){
            mSelectedArticle = arguments.getInt("selected")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.mNews.observe(this, Observer {
            mViewBinding.slider.adapter = NewsSliderFragmentPageAdapter(childFragmentManager,it)
            mViewBinding.slider.currentItem = mSelectedArticle
        })
    }


}
