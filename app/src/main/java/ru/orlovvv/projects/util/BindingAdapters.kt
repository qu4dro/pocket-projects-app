package ru.orlovvv.projects.util

import android.graphics.Paint
import android.widget.TextView
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

@BindingAdapter("strikeThrough")
fun strikeThrough(textView: TextView, strikeThrough: Boolean) {
    if (strikeThrough) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}
