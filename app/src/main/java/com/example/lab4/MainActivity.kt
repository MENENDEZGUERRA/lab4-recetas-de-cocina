package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.painter.ColorPainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter


data class RecipeItem(val name: String, val imageUrl: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    val itemList = remember { mutableStateListOf<RecipeItem>() } // This is the itemList we'll use

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenidos al programa",
                color = Color.Black,
                fontSize = 30.sp,
                modifier = Modifier.padding(16.dp)
            )

            var recipeName by remember { mutableStateOf("") }
            var imageUrl by remember { mutableStateOf("") }

            OutlinedTextField(
                value = recipeName,
                onValueChange = { recipeName = it },
                label = { Text("Nombre de la receta") },
                modifier = Modifier.padding(16.dp)
            )

            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("URL de la imagen") },
                modifier = Modifier.padding(16.dp)
            )

            Button(
                onClick = {
                    if (recipeName.isNotBlank() && imageUrl.isNotBlank()) {
                        val newItem = RecipeItem(recipeName, imageUrl)
                        if (!itemList.contains(newItem)) {
                            itemList.add(newItem)
                            recipeName = ""
                            imageUrl = ""
                        }
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Guardar Informacion")
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(itemList) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* TODO: Handle onClick */ }
                            .padding(8.dp)
                    ) {
                        Text(item.name, modifier = Modifier.weight(1f))
                        Image(
                            painter = rememberAsyncImagePainter(item.imageUrl),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp, 50.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    MainContent()
}
