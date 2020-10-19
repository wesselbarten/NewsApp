package nl.wesselbarten.newsapp.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nl.wesselbarten.newsapp.R
import nl.wesselbarten.newsapp.databinding.ActivityNewsBinding

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        binding.lifecycleOwner = this
    }

    override fun onSupportNavigateUp(): Boolean {
        return binding.fragmentContainerNews.findNavController().navigateUp()
    }
}