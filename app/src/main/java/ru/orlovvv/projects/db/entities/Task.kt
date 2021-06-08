package ru.orlovvv.projects.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    val description: String,
    val status: String,
    val isChecked: Boolean = false,
    val projectId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}