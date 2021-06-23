package ru.orlovvv.projects.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.db.entities.Task
import ru.orlovvv.projects.repository.ProjectsRepository
import ru.orlovvv.projects.util.ProjectStatus
import ru.orlovvv.projects.util.TaskStatus
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(private val projectsRepository: ProjectsRepository) :
    ViewModel() {

    var currentPagerTaskStatus = MutableLiveData<TaskStatus>(TaskStatus.TODO)

    private var _inProgressProjects =
        projectsRepository.getProjectsByStatus(ProjectStatus.IN_PROGRESS)
    val inProgressProjects
        get() = _inProgressProjects

    private var _archivedProjects = projectsRepository.getProjectsByStatus(ProjectStatus.ARCHIVED)
    val archivedProjects
        get() = _archivedProjects

    private var _trashedProjects = projectsRepository.getProjectsByStatus(ProjectStatus.TRASHED)
    val trashedProjects
        get() = _trashedProjects

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
        projectsRepository.insertProject(project)
    }

    fun createTask(task: Task) = viewModelScope.launch {
        projectsRepository.insertTask(task)
    }

    fun getTasks(projectId: Long) {
        _currentProjectTasksTodo =
            projectsRepository.getCurrentProjectTasks(projectId, TaskStatus.TODO)
        _currentProjectTasksDoing =
            projectsRepository.getCurrentProjectTasks(projectId, TaskStatus.DOING)
        _currentProjectTasksDone =
            projectsRepository.getCurrentProjectTasks(projectId, TaskStatus.DONE)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        projectsRepository.deleteTask(task)
    }

    fun updateTaskImportance(taskId: Long, isImportant: Boolean) = viewModelScope.launch {
        projectsRepository.updateTaskImportance(taskId, isImportant)
    }

    fun updateTaskStatus(taskId: Long, status: TaskStatus) = viewModelScope.launch {
        projectsRepository.updateTaskStatus(taskId, status)
    }

    fun updateProjectStatus(projectId: Long, newStatus: ProjectStatus) = viewModelScope.launch {
        projectsRepository.updateProjectsStatus(projectId, newStatus)
    }

    fun updateTaskCrossed(taskId: Long, crossed: Boolean) = viewModelScope.launch {
        projectsRepository.updateTaskCrossed(taskId, crossed)
    }
}