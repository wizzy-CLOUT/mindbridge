
package com.stanley.mindbridge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subject: String,
    val message: String,
    val appointmentDate: String,
)

