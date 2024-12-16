package com.example.instaclone.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.instaclone.data.model.posts.Comment

@Entity("posts")
data class PostsEntities(
    @PrimaryKey(autoGenerate = true) val uid: Int = 3,

    @ColumnInfo val caption: String,
    @ColumnInfo val likes: Int,
    @ColumnInfo(name = "profile_picture") val profilePicture: String,
    @ColumnInfo val username: String
)
