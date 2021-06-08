package ru.orlovvv.projects.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.orlovvv.projects.db.PocketProjectsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePocketProjectsDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        PocketProjectsDatabase::class.java,
        "kanban_db.db"
    ).build()

    @Singleton
    @Provides
    fun providePocketProjectsDao(db: PocketProjectsDatabase) = db.getPocketProjectsDAO()

}