package com.example.wellnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wellnessapp.model.Tip
import com.example.wellnessapp.model.WellnessTips
import com.example.wellnessapp.ui.theme.WellnessAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WellnessAppTheme {

                    WellnessApp()

                }
            }
        }
    }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "30 Days of Wellness") },

            )
        },
        content = {
            TipsList(Modifier.padding(it))
        }
    )
}



@Composable
fun TipCard(tip: Tip) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = tip.dayRes), style = MaterialTheme.typography.titleLarge)
            Text(text = stringResource(id = tip.titleRes), style = MaterialTheme.typography.titleMedium)
            Image(
                painter = painterResource(id = tip.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentScale = ContentScale.Crop
            )

            if (expanded) {
                Text(text = stringResource(id = tip.descriptionRes), style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
fun TipsList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(top = 16.dp)  // Add padding to the top
    ) {
        items(WellnessTips.tips) { tip ->
            TipCard(tip = tip)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessAppPreview() {
    WellnessApp()
}