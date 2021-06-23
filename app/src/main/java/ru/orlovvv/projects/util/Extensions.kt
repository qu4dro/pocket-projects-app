package ru.orlovvv.projects.util

import android.graphics.Rect
import android.os.Build
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.orlovvv.projects.R
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.db.entities.Task
import ru.orlovvv.projects.ui.ProjectsViewModel
import ru.orlovvv.projects.util.Constants.SPACING
import java.lang.reflect.Method

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

fun PopupMenu.setProjectActions(
    projectsViewModel: ProjectsViewModel,
    project: Project,
    popUp: PopupMenu
) {
    popUp.show()
    popUp.showIcons()
    this.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.deleteProject -> {
                projectsViewModel.updateProjectStatus(project.id!!, ProjectStatus.TRASHED)
                true
            }
            R.id.archiveProject -> {
                projectsViewModel.updateProjectStatus(project.id!!, ProjectStatus.ARCHIVED)
                true
            }

            R.id.restoreProject -> {
                projectsViewModel.updateProjectStatus(project.id!!, ProjectStatus.IN_PROGRESS)
                true
            }
            else -> false
        }
    }
}

fun PopupMenu.setTaskActions(
    projectsViewModel: ProjectsViewModel,
    task: Task,
    popUp: PopupMenu
) {
    popUp.show()
    popUp.showIcons()
    val checkBoxImportant = popUp.menu.findItem(R.id.markTaskAsImportant)
    val checkBoxCrossed = popUp.menu.findItem(R.id.crossTask)
    checkBoxImportant.isChecked = task.isImportant
    checkBoxCrossed.isChecked = task.isChecked
    this.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.deleteTask -> {
                projectsViewModel.deleteTask(task)
                true
            }
            R.id.markTaskAsImportant -> {
                checkBoxImportant.isChecked = !task.isImportant
                projectsViewModel.updateTaskImportance(task.id!!, !task.isImportant)
                true
            }

            R.id.crossTask -> {
                checkBoxCrossed.isChecked = !task.isChecked
                projectsViewModel.updateTaskCrossed(task.id!!, !task.isChecked)
                true
            }
            R.id.moveToTODO -> {
                projectsViewModel.updateTaskStatus(task.id!!, TaskStatus.TODO)
                true
            }
            R.id.moveToDOING -> {
                projectsViewModel.updateTaskStatus(task.id!!, TaskStatus.DOING)
                true
            }
            R.id.moveToDONE -> {
                projectsViewModel.updateTaskStatus(task.id!!, TaskStatus.DONE)
                true
            }
            else -> false
        }
    }
}

fun  PopupMenu.showIcons() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        this.setForceShowIcon(true)
    }else{
        try {
            val fields = this.javaClass.declaredFields
            for (field in fields) {
                if ("mPopup" == field.name) {
                    field.isAccessible = true
                    val menuPopupHelper = field[this]
                    val classPopupHelper =
                        Class.forName(menuPopupHelper.javaClass.name)
                    val setForceIcons: Method = classPopupHelper.getMethod(
                        "setForceShowIcon",
                        Boolean::class.javaPrimitiveType
                    )
                    setForceIcons.invoke(menuPopupHelper, true)
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}