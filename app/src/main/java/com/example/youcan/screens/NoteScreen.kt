package com.example.youcan.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavHostController, viewModel: MainViewModel, noteId: String?) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = when(DB_TYPE.value){
        TYPE_FIREBASE -> {
            notes.firstOrNull{ it.firebaseId == noteId } ?: Note()
        }
        TYPE_ROOM ->{
            notes.firstOrNull{ it.id == noteId?.toInt() } ?: Note()
        }
        else -> Note()
    }
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var subtitle by remember { mutableStateOf(Constants.Keys.EMPTY) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = Constants.Keys.EDIT_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(text = Constants.Keys.TITLE)},
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = { subtitle = it },
                        label = { Text(text = Constants.Keys.SUBTITLE)},
                        isError = subtitle.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(note =
                            Note(id = note.id, title = title, subtitle = subtitle, firebaseId = note.firebaseId)
                            ){
                                navController.navigate(NavRoute.Main.route)
                            }
                        }
                    ) {
                        Text(text = Constants.Keys.UPDATE_NOTE)
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.tapBarGround,
                    content = {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
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
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                    title = note.title
                                    subtitle = note.subtitle
                                    bottomSheetState.show()
                                    }
                                }
                            ){
                                Icon(imageVector = Icons.Rounded.Edit, contentDescription = "edit")
                            }
                        }
                    },
                    contentColor = Color.White,
                    elevation = 12.dp
                )
            }) {
                Column {
                    Box{
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopCenter)
                                .padding(16.dp),
                            text = note.title,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    NoteInfo(viewModel = viewModel, noteId = noteId)
                }
        }
    }
}

@Composable
fun NoteInfo(viewModel: MainViewModel, noteId: String?){
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = when(DB_TYPE.value){
        TYPE_FIREBASE -> {
            notes.firstOrNull{ it.firebaseId == noteId } ?: Note()
        }
        TYPE_ROOM ->{
            notes.firstOrNull{ it.id == noteId?.toInt() } ?: Note()
        }
        else -> Note()
    }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp),
                    text = "Info",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, bottom = 8.dp),
                    text = "Kcal: ${"%.1f".format(note.calories)}\n" +
                            "protein(g): ${"%.1f".format(note.proteins)}\n" +
                            "fat(g): ${"%.1f".format(note.fats)}\n" +
                            "carb(g): ${"%.1f".format(note.carbs)}",
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    text = "Comment",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.padding(start = 32.dp, bottom = 8.dp),
                    text = note.subtitle,
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp
                )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrevNoteScreen(){
    YouCanTheme() {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        NoteScreen(
            navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1"
        )
    }
}