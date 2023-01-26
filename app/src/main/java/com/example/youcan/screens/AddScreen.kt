package com.example.youcan.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.youcan.MainViewModel
import com.example.youcan.MainViewModelFactory
import com.example.youcan.di.FoodModel
import com.example.youcan.model.Note
import com.example.youcan.navigation.NavRoute
import com.example.youcan.ui.theme.YouCanTheme
import com.example.youcan.ui.theme.tapBarGround
import com.example.youcan.utils.Constants
import kotlin.random.Random
import androidx.compose.material.Text as Text

@Composable
fun AddScreen(navController: NavHostController, viewModel: MainViewModel, food: FoodModel.Food) {
    var title by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }

    //Random number
    var proteins = Random.nextDouble(0.0, 5.0)
    var fats = Random.nextDouble(0.0, 10.0)
    var carbs = Random.nextDouble(0.0, 25.0)
    var calories = proteins * 4 + fats * 9 + carbs * 4

    var isButtonEnabled by remember { mutableStateOf(false) }
    Scaffold (
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.tapBarGround,
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(NavRoute.Main.route)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "nav_back"
                            )
                        }
                    }
                },
                contentColor = Color.White,
                elevation = 12.dp
            )}
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = Constants.Keys.ADD_NEW_NOTE,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    isButtonEnabled = name.isNotEmpty()

                },
                label = { Text(text = Constants.Keys.NOTE_TITLE) },
                isError = name.isEmpty()

            )
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    isButtonEnabled = name.isNotEmpty()

                    val info = food.getInfo(it)
                    if (info.calories != 0.0 && info.proteins != 0.0 &&
                        info.fats != 0.0 && info.carbs != 0.0) {
                        calories = info.calories
                        proteins = info.proteins
                        fats = info.fats
                        carbs = info.carbs
                    }
                    if ((it.count { c: Char -> c == 'h' } > 4) or
                        (it.count { c: Char -> c == 'd' } > 4) or
                        (it.count { c: Char -> c == 'z' } > 4) or
                        (it.count { c: Char -> c == 'f' } > 4) or
                        (it.count { c: Char -> c == 'k' } > 4) or
                        (it.count { c: Char -> c == 'w' } > 4) or
                        (it.count { c: Char -> c == 't' } > 4) or
                        (it.count { c: Char -> c == 'q' } > 4) or
                        (it.count { c: Char -> c == 'j' } > 4) or
                        (it.length > 15)){
                        calories = 0.0
                        proteins = 0.0
                        fats = 0.0
                        carbs = 0.0
                    }
                },
                label = { Text(text = Constants.Keys.NOTE_FOOD_NAME) },
                isError = name.isEmpty()
            )

            OutlinedTextField(
                value = subtitle,
                onValueChange = {
                    subtitle = it
                    isButtonEnabled = title.isNotEmpty()
                    },
                label = { Text(text = Constants.Keys.NOTE_COMMENT) },
                isError = name.isEmpty()
            )

            Button(
                modifier = Modifier.padding(top = 16.dp),
                enabled = isButtonEnabled,
                onClick = {
                    viewModel.addNote(note = Note(name = name, title = title,
                        calories = calories,
                        proteins = proteins, fats = fats,
                        carbs = carbs, subtitle = subtitle)
                    ) {
                        navController.navigate(NavRoute.Main.route)
                    }
                }
            ) {
                Text(text = Constants.Keys.ADD_NOTE)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PrevAddScreen(){
//    YouCanTheme() {
//        val context = LocalContext.current
//        val mViewModel: MainViewModel =
//            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
//        NoteScreen(
//            navController = rememberNavController(),
//            viewModel = mViewModel,
//            noteId = "1"
//        )
//    }
//}