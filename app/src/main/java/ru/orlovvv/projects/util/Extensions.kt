package ru.orlovvv.projects.util

import android.graphics.Rect
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.orlovvv.projects.R
import ru.orlovvv.projects.db.entities.Task
import ru.orlovvv.projects.ui.PocketProjectsViewModel
import ru.orlovvv.projects.util.Constants.SPACING

fun RecyclerView.setStickersSpacing() {
    this.addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.set(SPACING, SPACING, SPACING, SPACING);
        }
    })
}

fun PopupMenu.setActions(
    pocketProjectsViewModel: PocketProjectsViewModel,
    task: Task,
    popUp: PopupMenu
) {
    popUp.show()
    val checkBoxImportant = popUp.menu.findItem(R.id.markTaskAsImportant)
    val checkBoxCrossed = popUp.menu.findItem(R.id.crossTask)
    checkBoxImportant.isChecked = task.isImportant
    checkBoxCrossed.isChecked = task.isChecked
    this.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.deleteTask -> {
                pocketProjectsViewModel.deleteTask(task)
                true
            }
            R.id.markTaskAsImportant -> {
                checkBoxImportant.isChecked = !task.isImportant
                pocketProjectsViewModel.updateTaskImportance(task.id!!, !task.isImportant)
                true
            }

            R.id.crossTask -> {
                checkBoxCrossed.isChecked = !task.isChecked
                pocketProjectsViewModel.updateTaskCrossed(task.id!!, !task.isChecked)
                true
            }
            R.id.moveToTODO -> {
                pocketProjectsViewModel.updateTaskStatus(task.id!!, TaskStatus.TODO)
                true
            }
            R.id.moveToDOING -> {
                pocketProjectsViewModel.updateTaskStatus(task.id!!, TaskStatus.DOING)
                true
            }
            R.id.moveToDONE -> {
                pocketProjectsViewModel.updateTaskStatus(task.id!!, TaskStatus.DONE)
                true
            }
            else -> false
        }
    }
}