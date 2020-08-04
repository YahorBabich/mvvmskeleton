package com.wsc.mvvmskeleton.di

import androidx.room.Room
import com.wsc.mvvmskeleton.db.AppDatabase
import org.koin.dsl.module

private const val DB_NAME = "database-name"
val databaseModules = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, DB_NAME).build() }
    single { get<AppDatabase>().historyDao() }
}