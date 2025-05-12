
package com.stanley.mindbridge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal")
data class Journal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val message: String,

)