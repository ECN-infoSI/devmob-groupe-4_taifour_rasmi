package com.example.simplichef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplichef.ui.theme.SimpliChefTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpliChefTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(message = "Cuisinons ensemble", modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

/**
 * Composable pour l'affichage du message de départ
 */
@Composable
fun GreetingText(message: String, modifier: Modifier = Modifier) {
        Text(
            text = message,
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            textAlign = TextAlign.Center,
            modifier=Modifier
        )

}
/**
 * Composable pour le button
 */
@Composable
fun StartingButton(textButton: String, modifier: Modifier = Modifier) {
    Button(
        onClick = {

        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
        ),
    ) {
        Text(
            text =textButton,
            fontFamily = FontFamily.Cursive,
        )

    }
}
/**
 * Composable pour l'affichage toute la page avec le back, le message et le button
 */
@Composable
fun GreetingImage(message: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.tagliatelle1)

    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
            {
            GreetingText(
                message = message,
                modifier = Modifier
                    .fillMaxSize()
            )
            StartingButton("Allons-y!")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpliChefTheme {
        GreetingImage(
            message = "Cuisinons ensemble !",
        )
    }
}