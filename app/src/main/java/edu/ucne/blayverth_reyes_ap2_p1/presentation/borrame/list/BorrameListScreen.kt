package edu.ucne.blayverth_reyes_ap2_p1.presentation.borrame.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucne.blayverth_reyes_ap2_p1.domain.borrame.model.Borrame

@Composable
fun BorrameListScreen(
    onAddBorrame: () -> Unit,
    onEditBorrame: (Int) -> Unit
){
    ListBody(
        onAddClick = onAddBorrame,
        onEditClick = onEditBorrame
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBody(
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit
){
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Elementos") }
            )
        },
        snackbarHost = {SnackbarHost(snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                modifier = Modifier.testTag("fab_add")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar"
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding).fillMaxSize()
        ){
            if(false){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center).testTag("Cargando")
                )
            }else{
                if(true){
                    Text(
                        text = "No hay datos",
                        modifier = Modifier.align(Alignment.Center).testTag("Vacio"),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }else{
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                    }
                }
            }
        }
    }
}

@Composable
fun BorrameItem(
    borrame: Borrame,
    onEdit: () -> Unit
){
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().clickable{onEdit()}.testTag("it_elemento")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


@Preview
@Composable
private fun BorrameListBodyPreview(){
    ListBody(
        onAddClick = {},
        onEditClick = {}
    )
}