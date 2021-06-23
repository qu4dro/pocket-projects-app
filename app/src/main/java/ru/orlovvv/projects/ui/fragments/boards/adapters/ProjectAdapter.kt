package ru.orlovvv.projects.ui.fragments.boards.adapters

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.ItemProjectBinding
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.db.entities.Task
import ru.orlovvv.projects.util.ProjectStatus
import ru.orlovvv.projects.util.TaskStatus

class ProjectAdapter :
    ListAdapter<Project, ProjectAdapter.ProjectViewHolder>(
        ProjectCallBack()
    ) {

    class ProjectViewHolder(val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.project = project
            binding.executePendingBindings()
        }

        fun getPopup(project: Project): PopupMenu {
            val popup = PopupMenu(binding.btnMore.context, binding.btnMore)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.project_popup_menu, popup.menu)
            with(popup.menu) {
                when (project.status) {
                    ProjectStatus.IN_PROGRESS.toString() -> {
                        findItem(R.id.restoreProject).isVisible = false
                        true
                    }
                    ProjectStatus.ARCHIVED.toString() -> {
                        findItem(R.id.archiveProject).isVisible = false
                        true
                    }
                    ProjectStatus.TRASHED.toString() -> {
                        findItem(R.id.deleteProject).isVisible = false
                        true
                    }
                    else -> false
                }
            }


            return popup
        }
    }

    class ProjectCallBack : DiffUtil.ItemCallback<Project>() {
        override fun areItemsTheSame(
            oldItem: Project,
            newItem: Project
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Project,
            newItem: Project
        ): Boolean {
            return newItem.id == oldItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectViewHolder {
        return ProjectViewHolder(
            ItemProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ProjectViewHolder,
        position: Int
    ) {
        val project = getItem(position)
        val popupMenu = holder.getPopup(project)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(project)
            }
        }
        holder.binding.btnMore.setOnClickListener {
            onPopupClickListener?.let {
                it(popupMenu, project)
            }
        }
        holder.bind(project)
    }

    private var onItemClickListener: ((Project) -> Unit)? = null

    fun setOnItemClickListener(listener: (Project) -> Unit) {
        onItemClickListener = listener
    }

    private var onPopupClickListener: ((PopupMenu, Project) -> Unit)? = null

    fun setOnPopupClickListener(listener: (PopupMenu, Project) -> Unit) {
        onPopupClickListener = listener
    }

}