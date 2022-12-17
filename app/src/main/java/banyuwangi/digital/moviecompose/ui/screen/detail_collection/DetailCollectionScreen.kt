package banyuwangi.digital.moviecompose.ui.screen.detail_collection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banyuwangi.digital.moviecompose.ui.theme.gilroySemiBold
import banyuwangi.digital.moviecompose.ui.theme.green500
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailCollectionScreen(
    id: Int,
    name: String,
    viewModel: DetailCollectionViewModel = koinViewModel()
) {
    val state = viewModel.state

    Column(modifier = Modifier.padding(all = 16.dp)) {
        Text(
            text = name,
            fontFamily = gilroySemiBold,
            fontSize = 24.sp,
            color = green500
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}