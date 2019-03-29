package hr.bagy94.factorynewsreader.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.bagy94.factorynewsreader.model.Article
import hr.bagy94.factorynewsreader.ui.newsdetail.ArticleDetailsFragment

class NewsSliderFragmentPageAdapter(fm:FragmentManager, private val news:List<Article>):FragmentPagerAdapter(fm) {
    /**
     * TODO: Reuse same fragment
     */
    override fun getItem(position: Int): Fragment {
        return ArticleDetailsFragment.newInstance(news[position])
    }

    override fun getCount(): Int = news.size

    override fun getPageTitle(position: Int): CharSequence? = news[position].title



    fun getPosition(title:String):Int{
        return news.indexOfFirst { it.title.equals(title) }
    }
}