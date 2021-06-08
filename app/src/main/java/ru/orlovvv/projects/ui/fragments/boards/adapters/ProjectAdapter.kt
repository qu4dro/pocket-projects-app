package ru.orlovvv.projects.ui.fragments.boards.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.orlovvv.projects.databinding.ItemProjectBinding
import ru.orlovvv.projects.db.entities.Project

class ProjectAdapter :
    ListAdapter<Project, ProjectAdapter.DefaultPatternViewHolder>(
        DefaultPatternCallBack()
    ) {

    class DefaultPatternViewHolder(private val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.project = project
            binding.executePendingBindings()
        }
    }

    class DefaultPatternCallBack : DiffUtil.ItemCallback<Project>() {
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
    ): DefaultPatternViewHolder {
        return DefaultPatternViewHolder(
            ItemProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: DefaultPatternViewHolder,
        position: Int
    ) {
        val breathingPattern = getItem(position)
        holder.bind(breathingPattern)
    }


}