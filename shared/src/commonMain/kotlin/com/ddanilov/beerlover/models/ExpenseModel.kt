package com.ddanilov.beerlover.models

class Expense(
    val id: String,
    val name: String,
    val category: Category
)

enum class Category {
    HOUSING,
    SHOPPING,
    TRANSPORTATION,
    FOOD_AND_DRINK
}
