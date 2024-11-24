package com.example.instaclone.views.pager_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.instaclone.R
import com.example.instaclone.data.model.GlobalDataModel
import com.example.instaclone.data.model.posts.Post
import com.example.instaclone.data.model.story.Story
import com.example.instaclone.pages.interfaces.PagerHandler
import com.example.instaclone.pages.interfaces.StoryClickHandler
import com.example.instaclone.views.recycler_view_adapters.home_adapters.PostsRvAdapter
import com.example.instaclone.views.recycler_view_adapters.home_adapters.StoryRvAdapter

class UserFeedFragment : Fragment(), StoryClickHandler {

    private lateinit var storiesRv: RecyclerView
    private lateinit var postsRv: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var _pagerHandler: PagerHandler

    companion object {
        fun newInstance(pageHandler: PagerHandler): UserFeedFragment {
            return UserFeedFragment().also {
                it._pagerHandler = pageHandler
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_feed, container, false)
        this.swipeRefreshLayout = view.findViewById(R.id.home_fragment_swipe_refresh_layout)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchData(view)
        setUpSwipeToRefreshListeners()
        setUpReloadDataViews(view)
    }

    private fun setUpStoriesRv(stories: List<Story>, fragmentView: View) {
        this.storiesRv = fragmentView.findViewById(R.id.user_feed_stories_recycler_view)
        this.storiesRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        this.storiesRv.adapter = StoryRvAdapter(stories, this)
        this.storiesRv.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    val canScrollHorizontally = rv.canScrollHorizontally(1) || rv.canScrollHorizontally(-1)

                    rv.parent.requestDisallowInterceptTouchEvent(canScrollHorizontally)

                    return false
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                    // Pas de traitement nécessaire ici
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                    // Pas de traitement nécessaire ici
                }
            })

    }

    private fun setUpPostsRv(posts: List<Post>, fragmentView: View) {
        this.postsRv = fragmentView.findViewById(R.id.user_feed_posts_recycler_view)
        this.postsRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.postsRv.adapter = PostsRvAdapter(posts)
    }

    private fun fetchData(fragmentView: View) {

        setUpPostsRv(listOf(), fragmentView)
        setUpStoriesRv(listOf(), fragmentView)
    }

    private fun getUserFeedStories(data: GlobalDataModel): List<Story> {
        return data.stories
    }

    private fun getUserFeedPosts(data: GlobalDataModel): List<Post> {
        return data.posts
    }

    override fun displayStoryContent(storiesUrls: List<String>, storiesDuration: List<Int>, username: String, pseudo: String, userProfilePicUrl: String) {

    }

    override fun addStory() {
        this._pagerHandler.displayMediaPage()
    }

    private fun setUpSwipeToRefreshListeners() {

    }

    private fun setUpReloadDataViews(fragmentView: View) {

    }
}