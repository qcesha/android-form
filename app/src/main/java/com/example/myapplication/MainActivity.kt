package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SurveyScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SurveyScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf(25f) }
    var gender by remember { mutableStateOf("male") }
    var subscribed by remember { mutableStateOf(false) }
    var showSummary by remember { mutableStateOf(false) }

    val isNameValid = name.isNotBlank()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Введите имя") },
            modifier = Modifier.fillMaxWidth(),
            isError = !isNameValid && showSummary
        )

        Spacer(Modifier.height(16.dp))


        Text("Возраст: ${age.toInt()}")
        Slider(
            value = age,
            onValueChange = { age = it },
            valueRange = 1f..100f,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))


        Text("Пол:")
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = gender == "male", onClick = { gender = "male" })
            Text("Мужской")
            Spacer(Modifier.width(16.dp))
            RadioButton(selected = gender == "female", onClick = { gender = "female" })
            Text("Женский")
        }

        Spacer(Modifier.height(16.dp))


        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = subscribed, onCheckedChange = { subscribed = it })
            Text("Хочу получать новости")
        }

        Spacer(Modifier.height(24.dp))


        Button(
            onClick = { showSummary = true },
            enabled = isNameValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Отправить")
        }

        Spacer(Modifier.height(24.dp))


        if (showSummary && isNameValid) {
            Text("Имя: $name")
            Text("Возраст: ${age.toInt()}")
            Text("Пол: ${if (gender == "male") "Мужской" else "Женский"}")
            Text("Подписка: ${if (subscribed) "Да" else "Нет"}")
        }
    }
}
