package nl.wesselbarten.newsapp.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import nl.wesselbarten.newsapp.databinding.FragmentNewsListBinding
import nl.wesselbarten.newsapp.domain.model.NewsItem
import nl.wesselbarten.newsapp.news.NewsItemsAdapter

class NewsListFragment : Fragment(), NewsItemsAdapter.NewsItemClickListener {

    private val newsItemsAdapter = NewsItemsAdapter(this)

    private lateinit var binding: FragmentNewsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onNewsItemClick(newsItem: NewsItem) {
        Toast.makeText(requireContext(), "TODO: OnNewsItemClick", Toast.LENGTH_LONG).show()
    }
}