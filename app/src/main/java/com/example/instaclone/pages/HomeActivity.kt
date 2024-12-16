package com.example.instaclone.pages

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.instaclone.R
import com.example.instaclone.app_utils.SHARED_PREF_KEY
import com.example.instaclone.pages.interfaces.PagerHandler
import com.example.instaclone.views.ViewPagerAdapter

class HomeActivity : AppCompatActivity(), PagerHandler {
    private lateinit var dymagramPager: ViewPager2
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Creation d'une Map <String, Any>
        this.sharedPref = this.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpMainPager()



        // Ajout d'une paire <String, String>
        sharedPref
            .edit()
            .putString("EMAIL", "user@myges.fr")
            .apply()

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

        val someString = this.sharedPref.getString("SOME_STRING", "DEFAULT_STRING")
    }

    override fun displayDirectMessagesPage() {
        this.dymagramPager.currentItem = 2
    }
}

