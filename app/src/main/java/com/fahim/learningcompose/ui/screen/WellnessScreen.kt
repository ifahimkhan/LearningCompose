package com.fahim.learningcompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fahim.learningcompose.ui.theme.LearningComposeTheme


@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    StatefulCounter(modifier)
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
    StatelessWidget(modifier = modifier, count = count, onIncrement = { count++ })
}

@Composable
private fun StatelessWidget(modifier: Modifier, count: Int, onIncrement: () -> Unit) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text(
                text = "You've had $count glasses.", modifier = modifier.padding(16.dp)
            )
        }

        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add One")
        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WaterCounterPreview() {
    LearningComposeTheme {
        StatefulCounter()
    }
}