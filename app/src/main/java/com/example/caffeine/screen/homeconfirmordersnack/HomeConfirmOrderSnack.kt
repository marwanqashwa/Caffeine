package com.example.caffeine.screen.homeconfirmordersnack

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeine.R
import com.example.caffeine.snacks
import com.example.caffeine.ui.theme.CaffeineBlack
import com.example.caffeine.ui.theme.CaffeineBrown
import com.example.caffeine.ui.theme.CaffeineWhite
import com.example.caffeine.ui.theme.sniglet_normal
import kotlinx.coroutines.delay

@Composable
fun HomeConfirmOrderSnack(
    snackID: Int, onConfirmClick: () -> Unit, onCloseClick: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(10)
        isVisible = true
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CaffeineWhite)
            .verticalScroll(
                state = rememberScrollState(), enabled = true
            )
            .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(color = CaffeineWhite)
                    .clickable(onClick = onCloseClick)
            ) {
                Icon(
                    painter = painterResource(R.drawable.cancel_ic),
                    contentDescription = null,
                    tint = CaffeineBlack,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.coffee_beans_ic),
                contentDescription = null,
                tint = CaffeineBrown,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "More Espresso, Less Depresso",
                fontFamily = sniglet_normal,
                fontWeight = FontWeight.W400,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.25.sp,
                color = CaffeineBrown,
                modifier = Modifier.padding(start = 6.dp, end = 6.dp)
            )
            Icon(
                painter = painterResource(R.drawable.coffee_beans_ic),
                contentDescription = null,
                tint = CaffeineBrown,
                modifier = Modifier.size(32.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .size(300.dp, 310.dp)
        ) {
            AnimatedVisibility(
                visible = isVisible, enter = fadeIn(tween(1000)) + scaleIn(tween(1000))
            ) {
                Image(
                    painter = painterResource(snacks[snackID]),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Bon appétit",
                fontFamily = sniglet_normal,
                fontWeight = FontWeight.W700,
                fontSize = 22.sp,
                lineHeight = 26.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xCC1F1F1F),
                modifier = Modifier.padding(top = 12.dp, end = 8.dp)
            )
            Icon(
                painter = painterResource(R.drawable.magic_wand),
                contentDescription = null,
                tint = Color(0xCC1F1F1F),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .defaultMinSize(minHeight = 56.dp)
                .padding(horizontal = 32.dp, vertical = 18.dp),
            colors = ButtonColors(
                containerColor = CaffeineBlack,
                contentColor = CaffeineWhite,
                disabledContainerColor = CaffeineWhite,
                disabledContentColor = CaffeineBlack
            ), onClick = onConfirmClick
        ) {
            Text(
                text = "Thank youuu",
                textAlign = TextAlign.Start,
                fontFamily = sniglet_normal,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.25.sp,
            )
            Icon(
                painter = painterResource(R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp)
            )
        }
    }
}