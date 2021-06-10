package ru.orlovvv.projects.ui.fragments.boards.adapters

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.ItemTaskBinding
import ru.orlovvv.projects.db.entities.Task
import ru.orlovvv.projects.util.TaskStatus

class TaskAdapter :
    ListAdapter<Task, TaskAdapter.TaskViewHolder>(
        TaskCallBack()
    ) {

    class TaskViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.task = task
            binding.executePendingBindings()
        }

        fun getPopup(task: Task): PopupMenu {
            val popup = PopupMenu(binding.btnMore.context, binding.btnMore)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.task_popup_menu, popup.menu)
            with(popup.menu) {
                when (task.status) {
                    TaskStatus.TODO.toString() -> {
                        findItem(R.id.moveToTODO).isVisible = false
                        true
                    }
                    TaskStatus.DOING.toString() -> {
                        findItem(R.id.moveToDOING).isVisible = false
                        true
                    }
                    TaskStatus.DONE.toString() -> {
                        findItem(R.id.moveToDONE).isVisible = false
                        true
                    }
                    else -> false
                }
            }


            return popup
        }
    }

    class TaskCallBack : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(
            oldItem: Task,
            newItem: Task
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Task,
            newItem: Task
        ): Boolean {
            return newItem.id == oldItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        val task = getItem(position)
        val popupMenu = holder.getPopup(task)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(task)
            }
        }
        holder.binding.btnMore.setOnClickListener {
            onPopupClickListener?.let {
                it(popupMenu, task)
            }
        }
        holder.bind(task)
    }

    private var onPopupClickListener: ((PopupMenu, Task) -> Unit)? = null

    fun setOnPopupClickListener(listener: (PopupMenu, Task) -> Unit) {
        onPopupClickListener = listener
    }

    private var onItemClickListener: ((Task) -> Unit)? = null

    fun setOnItemClickListener(listener: (Task) -> Unit) {
        onItemClickListener = listener
    }


}