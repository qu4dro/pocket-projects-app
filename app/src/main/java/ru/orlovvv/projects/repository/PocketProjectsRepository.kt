package ru.orlovvv.projects.repository

import ru.orlovvv.projects.db.PocketProjectsDAO
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.util.ProjectStatus
import javax.inject.Inject

class PocketProjectsRepository @Inject constructor(private val pocketProjectsDAO: PocketProjectsDAO) {

    suspend fun insertProject(project: Project) = pocketProjectsDAO.insertProject(project)

    fun getInProgressProjects() =
        pocketProjectsDAO.getInProgressProjects(ProjectStatus.IN_PROGRESS.toString())
}