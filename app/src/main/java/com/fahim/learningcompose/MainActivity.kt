package com.fahim.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.fahim.learningcompose.ui.theme.LearningComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearningComposeTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable {
        mutableStateOf(true)
    }
    Scaffold(modifier = modifier) { innerPadding ->

        if (shouldShowOnboarding) OnBoardingScreen(onContinueClicked = {
            shouldShowOnboarding = false
        }, modifier = modifier) else ListView()
    }
}

@Composable
fun ListView(
    modifier: Modifier = Modifier,
    dataList: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier.padding(vertical = 4.dp)) {
        items(items = dataList) { name ->
            Greeting(name = name)
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        var expanded by rememberSaveable {
            mutableStateOf(false)
        }
        val extraPadding by animateDpAsState(
            if (expanded) 48.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello ")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded = !expanded }
            ) {
                if (expanded) Text("Show less") else Text("Show more")
            }
        }
    }
}


@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to state hoisting ")
            ElevatedButton(
                onClick = { onContinueClicked() }
            ) {
                Text("continue")
            }
        }


    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
    LearningComposeTheme {
        OnBoardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearningComposeTheme {
        Greeting("Android")
    }
}

@Preview
@Composable
private fun MyAppPreview() {
    LearningComposeTheme {
        MyApp()
    }
}