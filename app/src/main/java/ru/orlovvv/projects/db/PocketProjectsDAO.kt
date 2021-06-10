package ru.orlovvv.projects.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.db.entities.Task

@Dao
interface PocketProjectsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("UPDATE tasks SET isImportant = :isImportant WHERE id = :taskId")
    suspend fun updateTaskImportance(taskId: Long, isImportant: Boolean): Int

    @Query("UPDATE tasks SET isChecked = :isChecked WHERE id = :taskId")
    suspend fun updateTaskChecked(taskId: Long, isChecked: Boolean): Int

    @Query("UPDATE tasks SET status = :status WHERE id = :taskId")
    suspend fun updateTaskStatus(taskId: Long, status: String): Int

    @Query("SELECT * FROM projects WHERE status = :status")
    fun getInProgressProjects(status: String): LiveData<List<Project>>

    @Query("SELECT * FROM tasks WHERE projectId = :projectId AND status = :status ORDER BY isChecked ASC, isImportant DESC, dateCreated ASC")
    fun getCurrentProjectTasks(projectId: Long, status: String): LiveData<List<Task>>


}