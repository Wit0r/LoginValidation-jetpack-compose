package com.example.loginvalidation.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginvalidation.MainViewModel
import com.example.loginvalidation.login.presentation.RegistrationFormEvent

@Composable
fun ScreenLogin() {
    val viewModel = viewModel<MainViewModel>()
    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is MainViewModel.ValidationEvent.Success -> {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = state.email,
            leadingIcon = {
                Icon(
                    Icons.Default.AccountBox,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            label = { Text(text = "E-mail") },
            singleLine = true,
            isError = state.emailError != null,
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                autoCorrect = true,
                imeAction = ImeAction.Next
            ),
            onValueChange = { viewModel.onEvent(RegistrationFormEvent.EmailChanged(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
        )
        if (state.emailError != null) {
            Text(
                text = state.emailError,
                color = MaterialTheme.colorScheme.error,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(Modifier.height(14.dp))

        OutlinedTextField(
            value = state.password,
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            label = { Text(text = "Password") },
            singleLine = true,
            isError = state.passwordError != null,
            shape = MaterialTheme.shapes.medium,
            keyboardActions = KeyboardActions { viewModel.onEvent(RegistrationFormEvent.Submit) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                autoCorrect = true,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colorScheme.error,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(Modifier.height(14.dp))

        Button(
            onClick = {
                viewModel.onEvent(RegistrationFormEvent.Submit)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp), shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Submit")
        }
    }
}