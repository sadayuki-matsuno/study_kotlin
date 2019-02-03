package com.example.study_kotiln

import com.beust.klaxon.Json

data class Repo (
    val id : Int,
    val name: String,
    @Json(name = "full_name") val fullName: String,
    val private: Boolean,
    val owner: Owner,
    val fork: Boolean,
    val size: Int = 0,
    @Json(name = "stargazers_count") val star: Int = 0,
    @Json(name = "open_issues") val openIssues: Int = 0
)

data class Owner (
    val login : String,
    val id: Int,
    @Json(name = "avatar_url") val avatarUrl: String = "",
    val url: String,
    val type: String
)
