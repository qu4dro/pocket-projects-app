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

    private var _currentProjectName = MutableLiveData<String>()
    val currentProjectName
        get() = _currentProjectName

    private var _currentProjectTasksTodo: LiveData<List<Task>> = MutableLiveData<List<Task>>()
    val currentProjectTasksTodo
        get() = _currentProjectTasksTodo

    private var _currentProjectTasksDoing: LiveData<List<Task>> = MutableLiveData<List<Task>>()

    val currentProjectTasksDoing
        get() = _currentProjectTasksDoing

    private var _currentProjectTasksDone: LiveData<List<Task>> = MutableLiveData<List<Task>>()
    val currentProjectTasksDone
        get() = _currentProjectTasksDone

    fun createProject(project: Project) = viewModelScope.launch {
        pocketProjectsRepository.insertProject(project)
    }

    fun createTask(task: Task) = viewModelScope.launch {
        pocketProjectsRepository.insertTask(task)
    }

    fun getTasks(projectId: Long) {
        _currentProjectTasksTodo =
            pocketProjectsRepository.getCurrentProjectTasks(projectId, TaskStatus.TODO)
        _currentProjectTasksDoing =
            pocketProjectsRepository.getCurrentProjectTasks(projectId, TaskStatus.DOING)
        _currentProjectTasksDone =
            pocketProjectsRepository.getCurrentProjectTasks(projectId, TaskStatus.DONE)
    }
}