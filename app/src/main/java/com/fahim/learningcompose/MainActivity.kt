package com.fahim.learningcompose

import Item
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fahim.learningcompose.ui.theme.LearningComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearningComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search)
            )
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)//minimum height of search bar
    )
}

@Composable
fun GridViewCardItem(modifier: Modifier = Modifier, item: Item) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(all = 4.dp)

    ) {
        Image(
            painter = painterResource(item.drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(bottom = 8.dp)
                .clip(shape = CircleShape)
                .background(shape = RectangleShape, color = Color.Red)


        )
        Text(
            text = item.name,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ListViewCardItem(
    modifier: Modifier = Modifier,
    item: Item
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = modifier
                    .size(80.dp)
                    .background(color = Color.Red),
                contentScale = ContentScale.Crop,
                painter = painterResource(item.drawable),
                contentDescription = null
            )
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun HorizontalScrollViewRow(
    modifier: Modifier = Modifier
) {
    val dataset = listOf(
        Item(R.drawable.ic_launcher_foreground, "Item1"),
        Item(R.drawable.ic_launcher_foreground, "Item2"),
        Item(R.drawable.ic_launcher_foreground, "Item3"),
        Item(R.drawable.ic_launcher_foreground, "Item4"),
        Item(R.drawable.ic_launcher_foreground, "Item5"),
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(items = dataset) { item ->
            GridViewCardItem(item = item)
        }
    }
}
@Preview
@Composable
private fun HorizontalScrollViewRowPreview() {
    LearningComposeTheme {
        HorizontalScrollViewRow()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
private fun ListViewCardItemPreview() {
    LearningComposeTheme {
        val item = Item(R.drawable.ic_launcher_foreground, name = "Sample")
        ListViewCardItem(item = item)
    }
}

@Preview(backgroundColor = 0xFFF5F0EE, showBackground = true)
@Composable
private fun GirdViewPreview() {
    LearningComposeTheme {
        val item = Item(R.drawable.ic_launcher_foreground, name = "Sample")
            GridViewCardItem(item = item)

    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    LearningComposeTheme {
        SearchBar()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearningComposeTheme {
        Greeting("Android")
    }
}