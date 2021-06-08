package ru.orlovvv.projects.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.db.entities.Task

@Database(entities = [Project::class, Task::class], version = 1, exportSchema = false)
abstract class PocketProjectsDatabase : RoomDatabase() {

    abstract fun getPocketProjectsDAO(): PocketProjectsDAO

}