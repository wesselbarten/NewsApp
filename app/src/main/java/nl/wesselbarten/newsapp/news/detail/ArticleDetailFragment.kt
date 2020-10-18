package nl.wesselbarten.newsapp.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nl.wesselbarten.newsapp.databinding.FragmentArticleDetailBinding
import nl.wesselbarten.newsapp.news.NewsViewModel

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
        return binding.root
    }
}