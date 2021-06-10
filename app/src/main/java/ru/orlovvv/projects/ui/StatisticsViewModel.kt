package ru.orlovvv.projects.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.orlovvv.projects.repository.StatisticsRepository
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(private val statisticsRepository: StatisticsRepository) :
    ViewModel() {

    private var _allProjectsCount = statisticsRepository.getProjectsCount()
    val allProjectsCount
        get() = _allProjectsCount

}