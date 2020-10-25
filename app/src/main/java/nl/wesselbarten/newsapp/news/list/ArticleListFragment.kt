package nl.wesselbarten.newsapp.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import nl.wesselbarten.newsapp.R
import nl.wesselbarten.newsapp.databinding.FragmentArticleListBinding
import nl.wesselbarten.newsapp.domain.model.Article
import nl.wesselbarten.newsapp.news.ArticlesAdapter
import nl.wesselbarten.newsapp.news.NewsViewModel
import nl.wesselbarten.newsapp.util.DividerItemDecoration
import nl.wesselbarten.newsapp.util.event.EventObserver

@AndroidEntryPoint
class ArticleListFragment : Fragment(), ArticlesAdapter.ArticleClickListener {

    private val articlesAdapter = ArticlesAdapter(this)
    private val viewModel: NewsViewModel by viewModels({ requireActivity() })

    private lateinit var binding: FragmentArticleListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.rcvArticles.adapter = articlesAdapter
        binding.rcvArticles.addItemDecoration(DividerItemDecoration(requireContext()))
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshTopHeadlines()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLiveData()
    }

    override fun onArticleClick(article: Article) {
        viewModel.selectArticle(article)
        findNavController().navigate(R.id.action_destination_article_list_to_destination_article_detail)
    }

    private fun setupLiveData() {
        viewModel.articles.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = false
            articlesAdapter.submitList(it)
        })
        viewModel.getArticlesFailed.observe(viewLifecycleOwner, EventObserver {
            binding.swipeRefresh.isRefreshing = false
            val errorMessage = getString(R.string.error_unable_to_get_articles)
            showErrorMessage(errorMessage)
        })
    }

    private fun showErrorMessage(errorMessage: String) {
        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}