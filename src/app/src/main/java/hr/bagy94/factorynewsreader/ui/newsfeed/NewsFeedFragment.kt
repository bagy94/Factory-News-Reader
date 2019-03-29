package hr.bagy94.factorynewsreader.ui.newsfeed

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hr.bagy94.common_module.BaseFragment
import hr.bagy94.factorynewsreader.MainActivity
import hr.bagy94.factorynewsreader.R
import hr.bagy94.factorynewsreader.adapter.NewsFeedAdapter
import hr.bagy94.factorynewsreader.databinding.FragmentNewsFeedBinding
import hr.bagy94.factorynewsreader.ui.utils.SimpleMessageDialogCreator
import kotlinx.android.synthetic.main.fragment_news_feed.*

class NewsFeedFragment : BaseFragment<NewsFeedViewModel,FragmentNewsFeedBinding>(), NewsFeedAdapter.OnArticleSelected{

    private lateinit var mNewsAdapter: NewsFeedAdapter

    override val provideViewId: Int
        get() = R.layout.fragment_news_feed
    override val provideViewModelClass: Class<NewsFeedViewModel>
        get() = NewsFeedViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            if(it is MainActivity){
                it.supportActionBar?.setDisplayHomeAsUpEnabled(false)
                it.supportActionBar?.title = getString(R.string.app_name)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNewsList()
        mViewModel.getNews().observe(this, Observer {
            if (it != null){
                mNewsAdapter.updateDataSet(it)
            }else{
                Log.d(NewsFeedFragment::class.java.name,"Greska")
            }
        })
        activity?.actionBar?.title = getString(R.string.app_name)
    }

    override fun onArticleSelectedListener(position:Int) {
        val bundle = bundleOf("selected" to position)
        findNavController().navigate(R.id.action_open_article,bundle)
    }

    fun showInternetConnectionDialog(){
        SimpleMessageDialogCreator.create(title = getString(R.string.error_label),
            message = getString(R.string.no_internet_connection_message),
            closeButtonLabel = getString(R.string.close_btn_label),
            context = context!!)
            .show()
    }

    private fun initNewsList(){
        mNewsAdapter = NewsFeedAdapter(this)
        news_list.layoutManager = LinearLayoutManager(context)
        news_list.adapter = mNewsAdapter
    }
}
