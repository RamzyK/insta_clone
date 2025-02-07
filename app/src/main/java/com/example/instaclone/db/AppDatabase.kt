package com.example.instaclone.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.instaclone.db.dao.PostsDao
import com.example.instaclone.db.entities.PostsEntities

@Database(entities = [PostsEntities::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun postDao(): PostsDao

}