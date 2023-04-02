package com.example.safenest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ContactModel")
data class ContactModel(
    val name: String,
    @PrimaryKey
    val number: String
)