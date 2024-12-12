package com.fahim.learningcompose

import Item
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
            AppInPortrait()
        }
    }
}

@Composable
fun AppInPortrait() {
    LearningComposeTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation()
            }) { innerPadding ->


            HomeScreen(Modifier.padding(innerPadding))


        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
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
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(all = 4.dp)

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
    modifier: Modifier = Modifier, item: Item
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.width(255.dp)
        ) {
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

@Composable
fun LazyHorizontalGridList(
    modifier: Modifier = Modifier
) {
    val dataset = listOf(
        Item(R.drawable.ic_launcher_foreground, "Item1"),
        Item(R.drawable.ic_launcher_foreground, "Item2"),
        Item(R.drawable.ic_launcher_foreground, "Item3"),
        Item(R.drawable.ic_launcher_foreground, "Item4"),
        Item(R.drawable.ic_launcher_foreground, "Item5"),
    )
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(items = dataset) { item ->
            ListViewCardItem(item = item)
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}


@Composable
private fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.app_name) {
            HorizontalScrollViewRow()
        }
        HomeSection(title = R.string.app_name) {
            LazyHorizontalGridList()
        }
        Spacer(Modifier.height(16.dp))


    }
}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(modifier = modifier, containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        NavigationBarItem(selected = true,
            onClick = { /*TODO*/ },
            label = { Text(text = stringResource(id = R.string.home)) },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            })
        NavigationBarItem(selected = false,
            onClick = { /*TODO*/ },
            label = { Text(text = stringResource(id = R.string.profile)) },
            icon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            })

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
private fun AppInPortraitPreview() {
    AppInPortrait()
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
private fun HomeSectionPreview() {
    LearningComposeTheme {
        HomeSection(title = R.string.app_name) {
            HomeScreen()
        }
    }
}

@Preview
@Composable
private fun BottomNavigationPreview() {
    LearningComposeTheme {
        BottomNavigation()
    }
}

@Preview
@Composable
private fun LazyHorizontalGridListPreview() {
    LearningComposeTheme {
        LazyHorizontalGridList()
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