package nl.wesselbarten.newsapp.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nl.wesselbarten.newsapp.databinding.ItemNewsItemBinding
import nl.wesselbarten.newsapp.domain.model.NewsItem

class NewsItemsAdapter(
    private val newsItemClickListener: NewsItemClickListener
) : ListAdapter<NewsItem, NewsItemsAdapter.NewsItemViewHolder>(NewsItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val itemBinding = ItemNewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsItemViewHolder(
        private val newsItemBinding: ItemNewsItemBinding
    ) : RecyclerView.ViewHolder(newsItemBinding.root) {

        fun bind(newsItem: NewsItem) {
            newsItemBinding.newsItem = newsItem
            newsItemBinding.root.setOnClickListener {
                newsItemClickListener.onNewsItemClick(newsItem)
            }
        }
    }

    object NewsItemCallback : DiffUtil.ItemCallback<NewsItem>() {

        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            TODO("Not yet implemented")
        }
    }

    interface NewsItemClickListener {
        fun onNewsItemClick(newsItem: NewsItem)
    }
}