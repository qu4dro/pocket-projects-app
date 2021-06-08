package ru.orlovvv.projects.repository

import ru.orlovvv.projects.db.PocketProjectsDAO
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.db.entities.Task
import ru.orlovvv.projects.util.ProjectStatus
import ru.orlovvv.projects.util.TaskStatus
import javax.inject.Inject

class PocketProjectsRepository @Inject constructor(private val pocketProjectsDAO: PocketProjectsDAO) {

    suspend fun insertProject(project: Project) = pocketProjectsDAO.insertProject(project)

    suspend fun insertTask(task: Task) = pocketProjectsDAO.insertTask(task)

    fun getInProgressProjects() =
        pocketProjectsDAO.getInProgressProjects(ProjectStatus.IN_PROGRESS.toString())

    fun getCurrentProjectTasks(projectId: Long, status: TaskStatus) =
        pocketProjectsDAO.getCurrentProjectTasks(projectId, status.toString())
}