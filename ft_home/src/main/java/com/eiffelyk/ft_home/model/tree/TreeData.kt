package com.eiffelyk.ft_home.model.tree

data class TreeData(
    val children: MutableList<TreeDataItem>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)
