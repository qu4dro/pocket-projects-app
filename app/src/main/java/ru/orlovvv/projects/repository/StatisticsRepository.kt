package ru.orlovvv.projects.repository

import ru.orlovvv.projects.db.PocketProjectsDAO
import javax.inject.Inject

class StatisticsRepository @Inject constructor(private val pocketProjectsDAO: PocketProjectsDAO) {

    fun getProjectsCount() = pocketProjectsDAO.getProjectsCount()
}