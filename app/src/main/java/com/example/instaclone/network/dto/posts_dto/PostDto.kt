package com.example.instaclone.network.dto.posts_dto

import com.google.gson.annotations.SerializedName

data class PostDto(
    val caption: String,
    @SerializedName("comments")
    val commentDtos: List<CommentDto>?,
    val content: String,
    val likes: Int,
    @SerializedName("post_id")
    val postId: String,
    @SerializedName("profile_picture")
    val profilePicture: String,
    val shares: Int,
    val timestamp: String,
    @SerializedName("user_id")
    val userId: String,
    val username: String
)