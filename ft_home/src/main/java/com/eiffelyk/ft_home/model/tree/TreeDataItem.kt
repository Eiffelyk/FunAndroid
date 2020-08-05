package com.eiffelyk.ft_home.model.tree

data class TreeDataItem(
    val children: MutableList<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)