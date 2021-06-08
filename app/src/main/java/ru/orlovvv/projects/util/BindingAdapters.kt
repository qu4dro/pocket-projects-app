package ru.orlovvv.projects.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.db.entities.Task
import ru.orlovvv.projects.ui.fragments.boards.adapters.ProjectAdapter
import ru.orlovvv.projects.ui.fragments.boards.adapters.TaskAdapter

@BindingAdapter("inProgressProjectsList")
fun bindInProgressProjectsList(recyclerView: RecyclerView, data: List<Project>?) {
    val adapter = recyclerView.adapter as ProjectAdapter
    adapter.submitList(data)
}

@BindingAdapter("tasksList")
fun bindTasksList(recyclerView: RecyclerView, data: List<Task>?) {
    val adapter = recyclerView.adapter as TaskAdapter
    adapter.submitList(data)
}
