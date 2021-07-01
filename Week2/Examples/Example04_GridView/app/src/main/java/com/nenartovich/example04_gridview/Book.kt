package com.nenartovich.example04_gridview

data class Book(val name: Int, val author: Int, val imageResource: Int,
                var isFavourite: Boolean, val imageUrl: String) {
    fun toogleFavourite() {
        this.isFavourite = !this.isFavourite
    }
}