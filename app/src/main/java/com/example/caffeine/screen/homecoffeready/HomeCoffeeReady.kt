package com.example.caffeine.screen.homecoffeready

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeine.R
import com.example.caffeine.screen.drawShadowUnderCircle
import com.example.caffeine.ui.theme.CaffeineBlack
import com.example.caffeine.ui.theme.CaffeineBrown
import com.example.caffeine.ui.theme.CaffeineWhite
import com.example.caffeine.ui.theme.sniglet_normal
import kotlinx.coroutines.delay

@Composable
fun HomeCoffeeReady(
    onTakeSnackClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(200)
        isVisible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CaffeineWhite)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .padding(start = 16.dp)
                .align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(color = Color(0xFFF5F5F5))
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

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(245.dp, 300.dp)

        ) {
            Image(
                painter = painterResource(R.drawable.empty_cup),
                contentDescription = null,
                modifier = Modifier.size(245.dp, 300.dp),
                contentScale = ContentScale.FillBounds
            )
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center)
            )

        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CoffeeSwitch(
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Take Away",
                    textAlign = TextAlign.Start,
                    fontFamily = sniglet_normal,
                    fontWeight = FontWeight.W700,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    letterSpacing = 0.25.sp,
                    color = Color(0xB21F1F1F),
                )
            }
            AnimatedVisibility(
                visible = isVisible, enter = slideInVertically(
                    initialOffsetY = { it }, animationSpec = tween(
                        durationMillis = 1000
                    )
                )
            ) {
                Button(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 56.dp)
                        .padding(horizontal = 32.dp, vertical = 18.dp)
                        .background(
                            color = CaffeineBlack, shape = CircleShape
                        )
                        .shadow(
                            elevation = 5.dp,
                            spotColor = CaffeineBlack,
                            ambientColor = CaffeineBlack,
                            shape = CircleShape
                        ), colors = ButtonColors(
                        containerColor = CaffeineBlack,
                        contentColor = CaffeineWhite,
                        disabledContainerColor = CaffeineWhite,
                        disabledContentColor = CaffeineBlack
                    ), onClick = onTakeSnackClick
                ) {
                    Text(
                        text = "Take snack",
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

        AnimatedVisibility(
            visible = isVisible, enter = slideInVertically(
                initialOffsetY = { -it }, animationSpec = tween(
                    durationMillis = 1000
                )
            ), modifier = Modifier.offset(y = (-210).dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .drawShadowUnderCircle(
                            shadowColor = "#80B94B23",
                            shadowRadius = 40f,
                            leftOffset = 76f,
                            topOffset = 16f,
                            rightOffset = 76f,
                            bottomOffset = 66f
                        )
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(color = CaffeineBrown)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.tick_ic),
                        contentDescription = null,
                        tint = CaffeineWhite,
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.Center)
                    )
                }
                Text(
                    text = "Your coffee is ready,\nEnjoy",
                    fontFamily = sniglet_normal,
                    fontWeight = FontWeight.W700,
                    fontSize = 22.sp,
                    lineHeight = 26.sp,
                    letterSpacing = 0.25.sp,
                    color = Color(0xDE1F1F1F),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
                )
                Image(
                    painter = painterResource(R.drawable.cup_cover),
                    contentDescription = null,
                    modifier = Modifier.size(260.dp, 70.dp)
                )
            }

        }
    }

}

@Composable
fun CoffeeSwitch(
    modifier: Modifier = Modifier
) {
    var isOn by remember { mutableStateOf(false) }
    val toggleOffsetX by animateDpAsState(
        targetValue = if (isOn) 38.dp else 0.dp, animationSpec = tween(400)
    )
    val backGroundColor by animateColorAsState(
        targetValue = if (isOn) Color(0xFFFFEEE7) else CaffeineBrown, animationSpec = tween(400)

    )
    val text = if (isOn) "OFF" else "ON"
    Box(modifier = modifier
        .clickable { isOn = !isOn }
        .width(78.dp)
        .height(40.dp)
        .clip(RoundedCornerShape(100.dp))
        .background(backGroundColor),
        contentAlignment = Alignment.CenterStart) {
        if (isOn) {
            Text(
                text = text,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = Color(0x991F1F1F),
                lineHeight = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp)
            )
        } else Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99FFFFFF),
            textAlign = TextAlign.End,
            lineHeight = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 14.dp)
        )

        Image(
            painter = painterResource(R.drawable.toggle_ic),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .offset(x = toggleOffsetX)
        )
    }
}



