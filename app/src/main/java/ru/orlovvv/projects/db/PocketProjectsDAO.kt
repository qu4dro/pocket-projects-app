package ru.orlovvv.projects.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.db.entities.Task

@Dao
interface PocketProjectsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    @Query("SELECT * FROM projects WHERE status = :status")
    fun getInProgressProjects(status: String): LiveData<List<Project>>

    @Query("SELECT * FROM tasks WHERE projectId = :projectId AND status = :status")
    fun getCurrentProjectTasks(projectId: Long, status: String): LiveData<List<Task>>

}