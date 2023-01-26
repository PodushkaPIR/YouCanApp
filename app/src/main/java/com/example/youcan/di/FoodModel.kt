package com.example.youcan.di


class FoodModel{
    data class FoodInfo(
        var name: String,
        var calories: Double,
        var proteins: Double,
        var fats: Double,
        var carbs: Double
    )

    object Food{
        private val foodList = listOf(
            FoodInfo("apple", 12.1, 13.2, 14.4, 0.0),
            FoodInfo("banana", 125.1, 13.2, 14.4, 0.0),
            FoodInfo("pineapple", 1.1, 13.2, 114.4, 1.0),
            FoodInfo("grass", 112.1, 13.2, 14.4, 0.0)
        )

        fun getInfo(name_title: String): FoodInfo{
            val info = FoodInfo("", 0.0, 0.0, 0.0, 0.0)
            for (i in foodList.indices){
                if (foodList[i].name == name_title){
                    info.name = foodList[i].name
                    info.calories = foodList[i].calories
                    info.proteins = foodList[i].proteins
                    info.fats = foodList[i].fats
                    info.carbs = foodList[i].carbs
                    break
                }
            }
            return info
        }
    }
}
