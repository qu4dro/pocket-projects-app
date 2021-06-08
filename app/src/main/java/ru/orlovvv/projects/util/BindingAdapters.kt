package ru.orlovvv.projects.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.ui.fragments.boards.adapters.ProjectAdapter

@BindingAdapter("inProgressProjectsList")
fun bindInProgressProjectsList(recyclerView: RecyclerView, data: List<Project>?) {
    val adapter = recyclerView.adapter as ProjectAdapter
    adapter.submitList(data)
}
