package com.example.app.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginForm(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Welcome to the", fontSize = 25.sp, color = MaterialTheme.colorScheme.secondary)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Rhythmic Realm", fontSize = 40.sp, color = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.height(150.dp))
            UsernameText.UsernameTextField(Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(5.dp))
            PasswordText.PasswordTextField(Modifier.fillMaxSize())
            Spacer(modifier = Modifier.height(50.dp))
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiaryContainer),
                onClick = { navController.navigate(route = "home") },
                modifier = Modifier.width(200.dp)
            ) {
                Text(
                    text = "Login",
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                )
            }
        }
    }
}

private object PasswordText {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PasswordTextField(
        modifier: Modifier = Modifier
    ) {
        var password by rememberSaveable { mutableStateOf("") }

        TextField(
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                focusedLabelColor = MaterialTheme.colorScheme.tertiaryContainer
            ),
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}

private object UsernameText {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UsernameTextField(
        modifier: Modifier = Modifier
    ) {
        var text by remember { mutableStateOf("") }

        TextField(
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                focusedLabelColor = MaterialTheme.colorScheme.tertiaryContainer
            ),
            value = text,
            onValueChange = { text = it },
            label = { Text("Username") }
        )
    }
}