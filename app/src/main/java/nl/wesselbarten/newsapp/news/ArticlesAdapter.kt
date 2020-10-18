package nl.wesselbarten.newsapp.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nl.wesselbarten.newsapp.databinding.ItemArticleBinding
import nl.wesselbarten.newsapp.domain.model.Article

class ArticlesAdapter(
    private val articleClickListener: ArticleClickListener
) : ListAdapter<Article, ArticlesAdapter.ArticleViewHolder>(ArticleItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemBinding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ArticleViewHolder(
        private val itemBinding: ItemArticleBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(article: Article) {
            itemBinding.article = article
            itemBinding.root.setOnClickListener {
                articleClickListener.onArticleClick(article)
            }
        }
    }

    object ArticleItemCallback : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.areContentsTheSame(newItem)
        }
    }

    interface ArticleClickListener {
        fun onArticleClick(article: Article)
    }
}