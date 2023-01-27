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
            FoodInfo("apple", 53.0, 0.3, 0.2, 14.1),
            FoodInfo("apples", 53.0, 0.3, 0.2, 14.1),
            FoodInfo("banana", 89.4, 1.1, 0.3, 23.2),
            FoodInfo("bananas", 89.4, 1.1, 0.3, 23.2),
            FoodInfo("pineapple", 50.8, 0.5, 0.1, 13.0),
            FoodInfo("cherry", 63.8, 1.1, 0.2, 16.1),
            FoodInfo("cherries", 63.8, 1.1, 0.2, 16.1),
            FoodInfo("cabbage", 23.2, 1.3, 0.1, 5.5),
            FoodInfo("cabbages", 23.2, 1.3, 0.1, 5.5),
            FoodInfo("sushi", 130.9, 4.2, 6.9, 13.9),
            FoodInfo("milk", 51.3, 3.5, 1.9, 4.9),
            FoodInfo("chicken egg", 145.8, 12.7, 9.7, 0.7),
            FoodInfo("chicken eggs", 145.8, 12.7, 9.7, 0.7),
            FoodInfo("chicken", 222.6, 23.7, 12.9, 0.0),
            FoodInfo("egg", 145.8, 12.7, 9.7, 0.7),
            FoodInfo("eggs", 145.8, 12.7, 9.7, 0.7),
            FoodInfo("salt", 0.0, 0.0, 0.0, 0.0),
            FoodInfo("sugar", 385.6, 0.0, 0.0, 99.8),
            FoodInfo("water", 0.0, 0.0, 0.0, 0.0),
            FoodInfo("ketchup", 100.7, 1.1, 0.1, 27.6),
            FoodInfo("pelmeni", 208.7, 10.5, 5.0, 29.4),
            FoodInfo("pizza", 262.9, 11.4, 9.8, 32.9),
            FoodInfo("sausage", 321.9, 26.2, 14.0, 1.4),
            FoodInfo("pork", 236.2, 1.1, 0.2, 0.8),
            FoodInfo("candy", 400.4, 0.0, 0.2, 98.2),
            FoodInfo("tea", 1.0, 0.0, 0.2, 0.3),
            FoodInfo("potato", 92.7, 2.5, 0.1, 21.0),
            FoodInfo("coconut", 455.3, 3.2, 28.3, 52.3),
            FoodInfo("strawberry", 31.9, 0.7, 0.3, 7.7),
            FoodInfo("blueberry", 56.2, 0.7, 0.0, 14.8),
            FoodInfo("meat", 221.2, 27.7, 12.2, 0.0),
            FoodInfo("spaghetti", 154.1, 5.3, 0.6, 30.6),
            FoodInfo("nut", 614.1, 21.1, 51.7, 20.9),
            FoodInfo("nuts", 614.1, 21.1, 51.7, 20.9),
            FoodInfo("donut", 415.9, 6.1, 22.3, 47.0),
            FoodInfo("donuts", 415.9, 6.1, 22.3, 47.0),
            FoodInfo("chocolate", 540.2, 7.8, 29.4, 58.9),
            FoodInfo("twix", 516.6, 4.9, 24.9, 64.6),
            FoodInfo("milkyway", 442.3, 4.0, 17.3, 72.3),
            FoodInfo("snickers", 497.6, 7.5, 0.2, 60.8),
            FoodInfo("tomato", 18.2, 0.9, 0.2, 3.9),
            FoodInfo("tomato juice", 17.2, 0.9, 0.2, 3.2),
            FoodInfo("carrot", 34.0, 0.8, 0.2,  8.3),
            FoodInfo("carrots", 34.0, 0.8, 0.2,  8.3),
            FoodInfo("cucumber", 15.3,  0.6, 0.1, 3.7),
            FoodInfo("cucumbers", 15.3,  0.6, 0.1, 3.7),
            FoodInfo("lemon", 29.2, 1.1, 0.3, 9.3),
            FoodInfo("lemons", 29.2, 1.1, 0.3, 9.3),
            FoodInfo("orange", 50.4, 0.9, 0.1, 12.4),
            FoodInfo("orange juice", 46.4, 0.7, 0.2, 11.4),
            FoodInfo("onion", 44.7, 1.4, 0.2, 10.1),
            FoodInfo("melon", 33.6, 0.8,  0.2, 8.3),
            FoodInfo("watermelon", 29.6, 0.6, 0.2, 7.7),
            FoodInfo("kiwi", 59.5, 1.1, 0.5, 14.9),
            FoodInfo("pear", 56.7, 0.4, 0.1, 14.9),
            FoodInfo("peach", 39.9, 0.9, 0.3, 9.7),
            FoodInfo("butter", 721.5, 0.9, 79.6, 0.1),
            FoodInfo("corn", 93.9, 3.4, 1.5, 21.0),
            FoodInfo("corns", 93.9, 3.4, 1.5, 21.0),
            FoodInfo("popcorn", 549.3, 8.5, 30.7, 57.0),
            FoodInfo("pepper", 248.5, 10.5, 3.2, 63.1),
            FoodInfo("red pepper", 27.8, 0.9, 0.2, 6.8),
            FoodInfo("garlic", 144.8, 6.4, 0.7, 32.5),
            FoodInfo("pumpkin", 18.2, 0.9, 0.2, 3.9),
            FoodInfo("peas", 82.4, 5.4, 0.2, 15.9),
            FoodInfo("pea", 82.4, 5.4, 0.2, 15.9),
            FoodInfo("hot dog", 330.3, 11.6, 29.6, 2.7),
            FoodInfo("hamburger", 242.5, 11.6, 11.8, 17.9),
            FoodInfo("cheeseburger", 242.5, 11.6, 11.8, 17.9),
            FoodInfo("cheese", 393.9, 22.7, 33.0, 3.2)
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
