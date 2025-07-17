package com.example.practiceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import com.example.practiceapp.ui.theme.PracticeAppTheme
import  androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.shadow(4.dp),
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ), title = { Text(text = "Practice App") })

                    },

                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                     Column(modifier = Modifier.padding(innerPadding)) {
                        MyApp()
                    }

                }
            }
        }
    }
}
@Composable
fun MyApp () {
    val moneyCounter = remember {
        mutableIntStateOf(0)
    }
    Surface (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(), color = MaterialTheme.colorScheme.primary) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text="$${100 + moneyCounter.intValue}", style = TextStyle(color = Color.White, fontSize = 35.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(100.dp))
            CreateCircle(moneyCounter.intValue){newValue ->
                moneyCounter.intValue = newValue
            }
            if (moneyCounter.intValue >= 100 ){
                Text(text = "You are making money!", style = TextStyle(color = Color.White))
            }
        }
    }
}

@Composable
fun CreateCircle(moneyCounter: Int, updateMoneyCounter: (Int)-> Unit){
    Card (modifier = Modifier
        .padding(3.dp)
        .size(100.dp)
        .clickable {
            updateMoneyCounter(moneyCounter + 10)
        },
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(4.dp)) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Tap", modifier = Modifier)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticeAppTheme {
        MyApp()
    }
}