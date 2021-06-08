package ru.orlovvv.projects.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    val description: String,
    val status: String,
    val projectId: Long,
    val isChecked: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}