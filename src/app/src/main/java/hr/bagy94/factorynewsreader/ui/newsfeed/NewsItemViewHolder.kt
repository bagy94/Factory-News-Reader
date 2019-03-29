package hr.bagy94.factorynewsreader.ui.newsfeed

import android.graphics.drawable.BitmapDrawable
import androidx.recyclerview.widget.RecyclerView
import hr.bagy94.factorynewsreader.adapter.NewsFeedAdapter
import hr.bagy94.factorynewsreader.databinding.ArticleListItemBinding
import hr.bagy94.factorynewsreader.model.Article

class NewsItemViewHolder(val binding: ArticleListItemBinding, private val listener: NewsFeedAdapter.OnArticleSelected): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Article,position:Int){
        binding.bindingItem = item
        binding.root.setOnClickListener {
            listener.onArticleSelectedListener(position)
        }
        binding.executePendingBindings()
    }
}