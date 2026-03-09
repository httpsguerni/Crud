package br.com.httpsguerni.crud.view

import android.content.Context
import android.database.Cursor
import android.icu.util.Currency
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.httpsguerni.crud.DataBaseController

class OperationScreens(var context: Context) {

    @Composable
    fun InsertScreen() {
        var titulo by remember { mutableStateOf("") }
        var author by remember { mutableStateOf("") }
        var editora by remember { mutableStateOf("") }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Text("DATABASE CRUD TEST - INSERT")

            TextField(
                modifier = Modifier.padding(5.dp),
                value = titulo,
                onValueChange = { titulo = it },
                placeholder = { Text("Title") }
            )

            TextField(
                modifier = Modifier.padding(5.dp),
                value = author,
                onValueChange = { author = it },
                placeholder = { Text("Author") }
            )

            TextField(
                modifier = Modifier.padding(5.dp),
                value = editora,
                onValueChange = { editora = it },
                placeholder = { Text("Publisher") }
            )

            Button(
                modifier = Modifier.padding(5.dp),
                onClick = { DataBaseController(context).insertData(titulo, author, editora) }
            ) { Text("Cadastrar") }
        }

    }

    @Composable
    fun SelectScreen(context: Context) {

        var livros by remember { mutableStateOf(listOf<Quadruple<Int, String, String, String>>()) }

        LaunchedEffect(Unit) {
            val cursor = DataBaseController(context).selectData()
            val lista = mutableListOf<Quadruple<Int, String, String, String>>()

            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"))
                    val titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"))
                    val autor = cursor.getString(cursor.getColumnIndexOrThrow("autor"))
                    val editora = cursor.getString(cursor.getColumnIndexOrThrow("editora"))
                    lista.add(Quadruple(id, titulo, autor, editora))
                } while (cursor.moveToNext())
            }
            cursor.close()
            livros = lista
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Text(
                "DATABASE CRUD TEST - SELECT",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )


            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("ID", style = MaterialTheme.typography.titleMedium)
                Text("Título", style = MaterialTheme.typography.titleMedium)
                Text("Autor", style = MaterialTheme.typography.titleMedium)
                Text("Editora", style = MaterialTheme.typography.titleMedium)
            }

            Divider()


            LazyColumn {
                items(livros) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item.first.toString())
                        Text(item.second)
                        Text(item.third)
                        Text(item.fourth)
                    }
                    Divider()
                }
            }
        }
    }


    data class Quadruple<A, B, C, D>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D
    )






    @Composable
    fun UpdateScreen() {
        var id by remember { mutableStateOf("") }
        var titulo by remember { mutableStateOf("") }
        var author by remember { mutableStateOf("") }
        var editora by remember { mutableStateOf("") }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Text("DATABASE CRUD TEST - UPDATE")

            TextField(
                modifier = Modifier.padding(5.dp),
                value = id,
                onValueChange = { id = it },
                placeholder = { Text("ID") }
            )


            TextField(
                modifier = Modifier.padding(5.dp),
                value = titulo,
                onValueChange = { titulo = it },
                placeholder = { Text("Title") }
            )

            TextField(
                modifier = Modifier.padding(5.dp),
                value = author,
                onValueChange = { author = it },
                placeholder = { Text("Author") }
            )

            TextField(
                modifier = Modifier.padding(5.dp),
                value = editora,
                onValueChange = { editora = it },
                placeholder = { Text("Publisher") }
            )

            Button(
                modifier = Modifier.padding(5.dp),
                onClick = { DataBaseController(context).updateData(id.toInt(), titulo, author, editora) }
            ) { Text("Alterar Dados") }
        }
    }


    @Composable
    fun DeleteScreen() {
        var id by remember { mutableStateOf("") }
        var titulo by remember { mutableStateOf("") }
        var author by remember { mutableStateOf("") }
        var editora by remember { mutableStateOf("") }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Text("DATABASE CRUD TEST - DELETE")

            TextField(
                modifier = Modifier.padding(5.dp),
                value = id,
                onValueChange = { id = it },
                placeholder = { Text("ID") }
            )


            Button(
                modifier = Modifier.padding(5.dp),
                onClick = { DataBaseController(context).deleteData(id.toInt()) }
            ) { Text("Deletar Dado") }
        }
    }
}