package com.example.youcan.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.youcan.MainViewModel
import com.example.youcan.MainViewModelFactory
import com.example.youcan.model.Note
import com.example.youcan.navigation.NavRoute
import com.example.youcan.ui.theme.YouCanTheme
import com.example.youcan.ui.theme.tapBarGround
import com.example.youcan.utils.Constants
import com.example.youcan.utils.DB_TYPE
import com.example.youcan.utils.TYPE_FIREBASE
import com.example.youcan.utils.TYPE_ROOM

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel){
    val context = LocalContext.current
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    Scaffold(
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
                        Text(
                            text = "WayNote",
                            fontSize =  16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (DB_TYPE.value.isNotEmpty()){
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    mViewModel.signOut {
                                        navController.navigate(NavRoute.Start.route){
                                            popUpTo(NavRoute.Start.route){
                                                inclusive = true
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    }
                },
                contentColor = Color.White,
                elevation = 12.dp
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.Add.route)
                },
                backgroundColor = MaterialTheme.colors.tapBarGround
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "AddIcons",
                    tint = Color.White
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ){
            items(notes) { note ->
                NoteItem(note = note, navController = navController, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun NoteItem (note: Note, navController: NavHostController, viewModel: MainViewModel){
    val context = LocalContext.current
    val noteId = when(DB_TYPE.value) {
        TYPE_FIREBASE -> note.firebaseId
        TYPE_ROOM -> note.id
        else -> Constants.Keys.EMPTY
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route + "/${noteId}")
            },
        elevation = 6.dp
    ){
        Box{
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(end = 24.dp),
                    text = note.title,
                    fontSize =  24.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )
                Text(
                    modifier = Modifier.padding(end = 24.dp),
                    text = "(100g)\n" +
                            "Kcal: ${"%.1f".format(note.calories)}\n" +
                            "protein(g): ${"%.1f".format(note.proteins)}\n" +
                            "fat(g): ${"%.1f".format(note.fats)}\n" +
                            "carb(g): ${"%.1f".format(note.carbs)}",
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = {
                    viewModel.deleteNote(note = note){}
//                    Toast.makeText(context, "Undo?", Toast.LENGTH_SHORT).show()
                }
            ){
                Icon(imageVector = Icons.Default.Delete, contentDescription = "delete note")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevMainScreen(){
    YouCanTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        MainScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}