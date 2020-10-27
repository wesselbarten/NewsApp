package nl.wesselbarten.newsapp.presentation.news.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import nl.wesselbarten.newsapp.databinding.FragmentArticleDetailBinding
import nl.wesselbarten.newsapp.domain.model.Article
import nl.wesselbarten.newsapp.presentation.news.NewsViewModel

@AndroidEntryPoint
class ArticleDetailFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModels({ requireActivity() })

    private lateinit var binding: FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLiveData()
    }

    private fun setupLiveData() {
        viewModel.selectedArticle.observe(viewLifecycleOwner, this::renderArticleContent)
    }

    private fun renderArticleContent(article: Article) {
        binding.article = article
        binding.btnViewArticle.setOnClickListener {
            openArticleUrl(article.url)
        }

        article.imageUrl?.let {
            Glide.with(this)
                .load(it)
                .into(binding.ivArticleImage)
        }
    }

    private fun openArticleUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}