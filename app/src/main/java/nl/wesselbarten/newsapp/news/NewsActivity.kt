package nl.wesselbarten.newsapp.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nl.wesselbarten.newsapp.R

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }
}