package ru.orlovvv.projects.repository

import ru.orlovvv.projects.db.PocketProjectsDAO
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.db.entities.Task
import ru.orlovvv.projects.util.ProjectStatus
import ru.orlovvv.projects.util.TaskStatus
import javax.inject.Inject

class ProjectsRepository @Inject constructor(private val pocketProjectsDAO: PocketProjectsDAO) {

    suspend fun insertProject(project: Project) = pocketProjectsDAO.insertProject(project)

    suspend fun insertTask(task: Task) = pocketProjectsDAO.insertTask(task)

    suspend fun deleteTask(task: Task) = pocketProjectsDAO.deleteTask(task)

    suspend fun updateTaskImportance(taskId: Long, isImportant: Boolean) =
        pocketProjectsDAO.updateTaskImportance(taskId, isImportant)

    suspend fun updateTaskStatus(taskId: Long, status: TaskStatus) =
        pocketProjectsDAO.updateTaskStatus(taskId, status.toString())

    suspend fun updateProjectsStatus(projectId: Long, newStatus: ProjectStatus) =
        pocketProjectsDAO.updateProjectStatus(projectId, newStatus.toString())

    suspend fun updateTaskCrossed(taskId: Long, crossed: Boolean) =
        pocketProjectsDAO.updateTaskChecked(taskId, crossed)

    fun getProjectsByStatus(status: ProjectStatus) =
        pocketProjectsDAO.getProjectsByStatus(status.toString())

    fun getCurrentProjectTasks(projectId: Long, status: TaskStatus) =
        pocketProjectsDAO.getCurrentProjectTasks(projectId, status.toString())
}