package ru.orlovvv.projects.ui.fragments.boards.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.orlovvv.projects.databinding.ItemTaskBinding
import ru.orlovvv.projects.db.entities.Task

class TaskAdapter :
    ListAdapter<Task, TaskAdapter.TaskViewHolder>(
        TaskCallBack()
    ) {

    class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.task = task
            binding.executePendingBindings()
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
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(task)
            }
        }
        holder.bind(task)
    }

    private var onItemClickListener: ((Task) -> Unit)? = null

    fun setOnItemClickListener(listener: (Task) -> Unit) {
        onItemClickListener = listener
    }

}