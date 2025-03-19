package com.example.fgfsample2.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.fgfsample2.model.Photos
import com.example.fgfsample2.viewmodel.PostViewModel

@Composable
fun PostList(viewModel: PostViewModel) {
    val posts by viewModel.posts

    if (posts.isEmpty()) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(posts) { post ->
                PostItem(post)
            }
        }
    }
}

@Composable
fun PostItem(photo: Photos) {

    var text by remember { mutableStateOf(convertToLike(photo.like)) }
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(text = photo.title, style = MaterialTheme.typography.titleMedium)
        Row () {
            AsyncImage(
                modifier = Modifier.width(100.dp).height(100.dp),
                model = "https://picsum.photos/200/300",
                contentDescription = photo.title
            )
            Button(onClick = {
                photo.like = photo.like.not()
                text = convertToLike(photo.like)
            }) { Text(text = text,) }
        }
    }
}

@Preview
@Composable
fun PreviewPostItem(){
    val testPost = Photos(1, 1, "Sample Image", "https://picsum.photos/200/300", "https://via.placeholder.com/150/92c952")
    PostItem(testPost)
}

fun convertToLike(dataVal: Boolean) = if (dataVal) "Liked" else "Like"