package com.example.instaclone.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.instaclone.db.entities.PostsEntities

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): List<PostsEntities>

    @Delete
    fun deletePost(post: PostsEntities)

    @Insert
    fun addPost(post: PostsEntities)


    @Insert
    suspend fun addPosts(posts: List<PostsEntities>)

}