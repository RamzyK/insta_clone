package com.example.instaclone.db

import androidx.room.Database
import com.example.instaclone.db.daos.PostsDao
import com.example.instaclone.db.entities.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1
)
abstract class AppDatabase {
    abstract fun postDao(): PostsDao
}