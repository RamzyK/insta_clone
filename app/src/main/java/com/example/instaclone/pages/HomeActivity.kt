package com.example.instaclone.pages

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.instaclone.R
import com.example.instaclone.pages.interfaces.PagerHandler
import com.example.instaclone.views.ViewPagerAdapter

class HomeActivity : AppCompatActivity(), PagerHandler {
    private lateinit var dymagramPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpMainPager()
    }

    private fun setUpMainPager() {
        this.dymagramPager = findViewById(R.id.main_app_pager)

        val mainPagerAdapter = ViewPagerAdapter(this, this)
        this.dymagramPager.adapter = mainPagerAdapter

        displayHomePage()
    }

    override fun displayMediaPage() {
        this.dymagramPager.currentItem = 0
    }

    override fun displayHomePage() {
        this.dymagramPager.currentItem = 1
    }

    override fun displayDirectMessagesPage() {
        this.dymagramPager.currentItem = 2
    }
}

