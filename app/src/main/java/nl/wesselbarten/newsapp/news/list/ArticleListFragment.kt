package nl.wesselbarten.newsapp.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nl.wesselbarten.newsapp.databinding.FragmentArticleListBinding
import nl.wesselbarten.newsapp.domain.model.Article
import nl.wesselbarten.newsapp.news.ArticlesAdapter
import nl.wesselbarten.newsapp.news.NewsViewModel

@AndroidEntryPoint
class ArticleListFragment : Fragment(), ArticlesAdapter.ArticleClickListener {

    private val newsItemsAdapter = ArticlesAdapter(this)
    private val viewModel: NewsViewModel by viewModels({ requireActivity() })

    private lateinit var binding: FragmentArticleListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onArticleClick(article: Article) {
        Toast.makeText(requireContext(), "TODO: OnNewsItemClick", Toast.LENGTH_LONG).show()
    }
}