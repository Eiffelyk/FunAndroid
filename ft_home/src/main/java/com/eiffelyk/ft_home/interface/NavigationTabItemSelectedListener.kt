package com.eiffelyk.ft_home.`interface`

import com.eiffelyk.ft_home.model.nav.NavigationItem

interface NavigationTabItemSelectedListener {
    fun onItemSelected(item: NavigationItem, position: Int)
}