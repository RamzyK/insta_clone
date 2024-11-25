package com.example.instaclone.network.mapper

import com.example.dymagram.network.mapper.mapFollowingDtoToFollowingModel
import com.example.dymagram.network.mapper.mapMessageDtoToMessageModel
import com.example.dymagram.network.mapper.mapPostDtoToPostModel
import com.example.dymagram.network.mapper.mapStoryDtoToStoryModel
import com.example.instaclone.data.model.GlobalDataModel
import com.example.instaclone.network.dto.GlobalModelDto

fun mapGlobalDataDtoToGlobalDataModel(dto: GlobalModelDto): GlobalDataModel {
    return GlobalDataModel(
        followings = dto.followings.map { mapFollowingDtoToFollowingModel(it) },
        posts = dto.posts.map { mapPostDtoToPostModel(it) },
        stories = dto.stories.map { mapStoryDtoToStoryModel(it) },
        messages = dto.messages.map { mapMessageDtoToMessageModel(it) }
    )
}