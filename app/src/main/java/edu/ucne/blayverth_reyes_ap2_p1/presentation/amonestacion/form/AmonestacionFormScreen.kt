package edu.ucne.blayverth_reyes_ap2_p1.presentation.amonestacion.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmonestacionFormScreen(
    viewModel: AmonestacionFormViewModel = hiltViewModel(),
    onBack: () -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.saved, state.deleted) {
        if(state.saved || state.deleted){
            onBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if(state.isNew) "Nueva amonestacion" else "Editar amonestacion") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.nombres,
                onValueChange = { viewModel.onEvent(AmonestacionFormUiEvent.NombresChanged(it)) },
                label = { Text("Nombres") },
                modifier = Modifier.fillMaxWidth().testTag("nombres"),
                isError = state.nombresError != null,
                supportingText = state.nombresError?.let { errorMsg -> { Text(errorMsg) } },
                singleLine = true
            )

            OutlinedTextField(
                value = state.razon,
                onValueChange = { viewModel.onEvent(AmonestacionFormUiEvent.RazonChanged(it)) },
                label = { Text("Razon") },
                modifier = Modifier.fillMaxWidth().testTag("razon"),
                isError = state.razonError != null,
                supportingText = state.razonError?.let { errorMsg -> { Text(errorMsg) } },
                singleLine = true
            )

            OutlinedTextField(
                value = state.monto,
                onValueChange = { viewModel.onEvent(AmonestacionFormUiEvent.MontoChanged(it)) },
                label = { Text("Monto") },
                modifier = Modifier.fillMaxWidth().testTag("monto"),
                isError = state.montoError != null,
                supportingText = state.montoError?.let { errorMsg -> { Text(errorMsg) } },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { viewModel.onEvent(AmonestacionFormUiEvent.Save) },
                    modifier = Modifier.weight(1f).testTag("btn_save"),
                    enabled = !state.isSaving
                ) {
                    if(state.isSaving){
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Guardar")
                    }
                }

                if(!state.isNew){
                    Button(
                        onClick = { viewModel.onEvent(AmonestacionFormUiEvent.Delete) },
                        modifier = Modifier.weight(1f).testTag("btn_delete"),
                        enabled = !state.isDeleting,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError
                        )
                    ) {
                        if(state.isDeleting){
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.onError,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AmonestacionFormBodyPreview(){
    AmonestacionFormScreen(
        onBack = {}
    )
}