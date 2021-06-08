package ru.orlovvv.projects.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.db.entities.Task
import ru.orlovvv.projects.repository.PocketProjectsRepository
import ru.orlovvv.projects.util.TaskStatus
import javax.inject.Inject

@HiltViewModel
class PocketProjectsViewModel @Inject constructor(private val pocketProjectsRepository: PocketProjectsRepository) :
    ViewModel() {

    var currentPagerTaskStatus = MutableLiveData<TaskStatus>(TaskStatus.TODO)

    private var _inProgressProjects = pocketProjectsRepository.getInProgressProjects()
    val inProgressProjects
        get() = _inProgressProjects

    private var _currentProjectId = MutableLiveData<Long>()
    val currentProjectId
        get() = _currentProjectId

    private var _currentProjectTasksTodo = Transformations.switchMap(_currentProjectId) {
        pocketProjectsRepository.getCurrentProjectTasks(it, TaskStatus.TODO)
    }
    val currentProjectTasksTodo
        get() = _currentProjectTasksTodo

    private var _currentProjectTasksDoing = Transformations.switchMap(_currentProjectId) {
        pocketProjectsRepository.getCurrentProjectTasks(it, TaskStatus.DOING)
    }
    val currentProjectTasksDoing
        get() = _currentProjectTasksDoing

    private var _currentProjectTasksDone = Transformations.switchMap(_currentProjectId) {
        pocketProjectsRepository.getCurrentProjectTasks(it, TaskStatus.DONE)
    }
    val currentProjectTasksDone
        get() = _currentProjectTasksDone

    fun createProject(project: Project) = viewModelScope.launch {
        pocketProjectsRepository.insertProject(project)
    }

    fun createTask(task: Task) = viewModelScope.launch {
        pocketProjectsRepository.insertTask(task)
    }
}