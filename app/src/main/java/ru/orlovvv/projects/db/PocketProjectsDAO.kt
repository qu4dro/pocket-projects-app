package ru.orlovvv.projects.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.orlovvv.projects.db.entities.Project

@Dao
interface PocketProjectsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project): Long

    @Query("SELECT * FROM projects WHERE status = :status")
    fun getInProgressProjects(status: String): LiveData<List<Project>>

}