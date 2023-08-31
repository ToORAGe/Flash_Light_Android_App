package com.example.flashlight

import androidx.compose.runtime.*
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.flashlight.ui.theme.FlashLightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var cameraM = getSystemService(CAMERA_SERVICE) as CameraManager
        setContent {
            FlashLightComposable(cameraM = cameraM)
        }
    }
}

@Composable
fun FlashLightComposable(cameraM : CameraManager){
    val state = remember{
        mutableStateOf(false)
    }
    var buttonText by remember {
        mutableStateOf("OFF")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ){
        Button(onClick = {
            val rearCamera = cameraM.cameraIdList[0]
            if(!state.value){
                cameraM.setTorchMode(rearCamera, true)
                state.value = true
                buttonText = "ON"
            }else{
                cameraM.setTorchMode(rearCamera, false)
                state.value = false
                buttonText = "OFF"
            }
        }
        ){ Text (text = "$buttonText")

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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlashLightTheme {
        Greeting("Android")
    }
}