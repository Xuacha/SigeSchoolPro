package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PersonalProfile(
    val id: String,
    val userId: String,
    val roleId: String,
    val cvData: CvData,
    val originalFilePath: String?,
    val status: String,
    val version: Int
)

@Serializable
data class CvData(
    val personalInfo: PersonalInfo,
    val contact: ContactInfo,
    val education: List<Education>,
    val experience: List<Experience>,
    val skills: Skills,
    val jobInfo: JobInfo
)

@Serializable
data class PersonalInfo(
    val fullName: String,
    val dni: String,
    val birthDate: String,
    val nationality: String,
    val gender: String
)

@Serializable
data class ContactInfo(
    val email: String,
    val phone: String,
    val address: String
)

@Serializable
data class Education(
    val degree: String,
    val institution: String,
    val graduationYear: String,
    val fieldOfStudy: String
)

@Serializable
data class Experience(
    val company: String,
    val position: String,
    val years: String,
    val description: String
)

@Serializable
data class Skills(
    val languages: List<LanguageSkill>,
    val technicalSkills: List<String>,
    val certifications: List<String>
)

@Serializable
data class LanguageSkill(
    val language: String,
    val level: String
)

@Serializable
data class JobInfo(
    val joiningDate: String,
    val contractType: String,
    val currentPosition: String,
    val assignedArea: String
)
