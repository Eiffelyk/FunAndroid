package com.eiffelyk.ft_home.model.nav

data class NavigationItem(
    val articles: MutableList<NavigationItemDetail>,
    val cid: Int,
    var isSelect: Boolean,
    val name: String
)