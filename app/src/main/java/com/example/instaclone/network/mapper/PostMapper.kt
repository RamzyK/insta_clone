package com.example.dymagram.network.mapper

import com.example.instaclone.data.model.posts.Comment
import com.example.instaclone.data.model.posts.Post
import com.example.instaclone.network.dto.posts_dto.PostDto


fun mapPostDtoToPostModel(dto: PostDto): Post {
    return Post(
        caption = dto.caption,
        comments = dto.commentDtos?.map {
            Comment(
                comment = it.comment,
                timestamp = it.timestamp,
                user_id = it.user_id,
                username = it.username
            )
        } ?: listOf(),
        content = dto.content,
        likes = dto.likes,
        post_id = dto.postId,
        profile_picture = dto.profilePicture,
        shares = dto.shares,
        timestamp = dto.timestamp,
        user_id = dto.userId,
        username = dto.username
    )
}