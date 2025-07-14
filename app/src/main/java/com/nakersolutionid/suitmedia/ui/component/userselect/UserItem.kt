package com.nakersolutionid.suitmedia.ui.component.userselect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.nakersolutionid.suitmedia.R
import com.nakersolutionid.suitmedia.data.remote.response.DataItem
import com.nakersolutionid.suitmedia.ui.theme.SuitmediaTheme

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    data: DataItem,
    onUserClick: (DataItem) -> Unit
) {
    // Add verticalAlignment = Alignment.CenterVertically here
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onUserClick(data) }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data.avatar)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .aspectRatio(1f)
                .padding(16.dp)
                .clip(CircleShape),
        )
        // This Column will now be centered vertically within the Row
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${data.firstName} ${data.lastName}",
                fontWeight = FontWeight.W500,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = data.email,
                fontWeight = FontWeight.W500,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserItemPreview() {
    SuitmediaTheme {
        val data = DataItem(
            "Naufal",
            1,
            "https://picsum.photos/200/300",
            "Azka",
            "mhmdazkanfl@gmail.com"
        )
        UserItem(data = data, onUserClick = {})
    }
}