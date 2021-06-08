package ru.orlovvv.projects.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.repository.PocketProjectsRepository
import javax.inject.Inject

@HiltViewModel
class PocketProjectsViewModel @Inject constructor(private val pocketProjectsRepository: PocketProjectsRepository) :
    ViewModel() {

    private var _inProgressProjects = pocketProjectsRepository.getInProgressProjects()
    val inProgressProjects
        get() = _inProgressProjects


    fun createProject(project: Project) = viewModelScope.launch {
        pocketProjectsRepository.insertProject(project)
    }
}