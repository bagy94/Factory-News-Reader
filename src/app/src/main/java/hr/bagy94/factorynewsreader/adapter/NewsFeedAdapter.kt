package hr.bagy94.factorynewsreader.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import hr.bagy94.factorynewsreader.R
import hr.bagy94.factorynewsreader.databinding.ArticleListItemBinding
import hr.bagy94.factorynewsreader.model.Article
import hr.bagy94.factorynewsreader.ui.newsfeed.NewsItemViewHolder

class NewsFeedAdapter(private val onArticleSelected: OnArticleSelected):RecyclerView.Adapter<NewsItemViewHolder>() {

    var mDataSet:List<Article>? = null
        private set

    fun updateDataSet(data: List<Article>){
        mDataSet = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ArticleListItemBinding = DataBindingUtil.inflate(inflater, R.layout.article_list_item,parent,false)
        return NewsItemViewHolder(binding,onArticleSelected)
    }

    override fun getItemCount(): Int = mDataSet?.size ?: 0

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        mDataSet?.let {
            val article = it[position]
            holder.bind(article,position)
        }
    }

    interface OnArticleSelected {
        fun onArticleSelectedListener(position:Int)
    }
}