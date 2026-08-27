package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Announcement(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val date: Long = 0,
    @SerialName("author_id")
    val authorId: String = "",
    @SerialName("institution_id")
    val institutionId: String = "",
    val target: String = "ALL" // ALL, TEACHERS, PARENTS, STUDENTS
)
