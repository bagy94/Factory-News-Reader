package hr.bagy94.factorynewsreader.ui.newsdetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import hr.bagy94.common_module.BaseFragment
import hr.bagy94.factorynewsreader.R
import hr.bagy94.factorynewsreader.databinding.FragmentArticleDetailsBinding
import hr.bagy94.factorynewsreader.model.Article



class ArticleDetailsFragment :BaseFragment<ArticleDetailsViewModel,FragmentArticleDetailsBinding>(){
    override val provideViewId: Int
        get() = R.layout.fragment_article_details
    override val provideViewModelClass: Class<ArticleDetailsViewModel>
        get() = ArticleDetailsViewModel::class.java

    private var mArticle:Article? = null

    override fun onParseArguments(arguments: Bundle) {
        if(arguments.containsKey(ARG_ARTICLE)){
            mArticle = arguments.getParcelable(ARG_ARTICLE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.article = mArticle
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).supportActionBar?.title = mArticle?.title
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.actionBar?.title = mArticle?.title
    }

    companion object {
        private const val ARG_ARTICLE = "article"
        @JvmStatic
        fun newInstance(article: Article):ArticleDetailsFragment{
            val bundle = Bundle().apply {
                putParcelable(ARG_ARTICLE,article)
            }
            return ArticleDetailsFragment().apply { arguments = bundle }
        }
    }
}