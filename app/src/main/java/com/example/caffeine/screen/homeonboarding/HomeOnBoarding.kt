package com.example.caffeine.screen.homeonboarding

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeine.R
import com.example.caffeine.composable.TopBar
import com.example.caffeine.screen.ovalShadowShape
import com.example.caffeine.ui.theme.CaffeineBlack
import com.example.caffeine.ui.theme.CaffeineWhite
import com.example.caffeine.ui.theme.sniglet_normal

@Composable
fun HomeOnBoarding(
    onButtonClick: () -> Unit
) {

    val infiniteTransition = rememberInfiniteTransition()
    val alpha = infiniteTransition.animateFloat(
        initialValue = 0.2f, targetValue = 1f, label = "", animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 600
            ), repeatMode = RepeatMode.Reverse
        )
    )
    val offset = infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 20f, label = "", animationSpec = infiniteRepeatable(
            animation = tween(1700), repeatMode = RepeatMode.Reverse
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CaffeineWhite)
            .verticalScroll(
                state = rememberScrollState(), enabled = true
            )
            .padding(vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            Modifier.padding(bottom = 24.dp)
        )
        TextWithStarsSection(
            modifier = Modifier
                .defaultMinSize(minHeight = 200.dp, minWidth = 188.dp)
                .align(Alignment.CenterHorizontally), starsAlpha = alpha.value
        )
        Image(
            painter = painterResource(R.drawable.gost_caffeine),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(244.dp)
                .align(Alignment.CenterHorizontally)
                .offset(y = offset.value.dp)
        )
        Box(
            modifier = Modifier
                .width((178 - offset.value).dp)
                .height(28.dp)
                .offset(y = -(offset.value - 20).dp)
                .ovalShadowShape()

        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .defaultMinSize(minHeight = 56.dp)
                .padding(horizontal = 32.dp, vertical = 18.dp),
            colors = ButtonColors(
                containerColor = CaffeineBlack,
                contentColor = CaffeineWhite,
                disabledContainerColor = CaffeineWhite,
                disabledContentColor = CaffeineBlack
            )
        ) {
            Text(
                text = "bring my coffee",
                textAlign = TextAlign.Start,
                fontFamily = sniglet_normal,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.25.sp,
            )
            Icon(
                painter = painterResource(R.drawable.coffee_cup_ic),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp)
            )
        }

    }

}


@Composable
fun TextWithStarsSection(
    modifier: Modifier = Modifier, starsAlpha: Float
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "Hocus\nPocus\nI Need Coffee\nto Focus",
            textAlign = TextAlign.Center,
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W400,
            fontSize = 32.sp,
            lineHeight = 50.sp,
            letterSpacing = 0.25.sp,
            color = Color(0xDE1F1F1F),
        )
        Icon(
            painter = painterResource(R.drawable.star_caffeine),
            contentDescription = null,
            tint = CaffeineBlack,
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.BottomEnd)
                .offset(x = (20).dp)
                .alpha(starsAlpha)
        )
        Icon(
            painter = painterResource(R.drawable.star_caffeine),
            contentDescription = null,
            tint = CaffeineBlack,
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.TopEnd)
                .alpha(starsAlpha)

        )
        Icon(
            painter = painterResource(R.drawable.star_caffeine),
            contentDescription = null,
            tint = CaffeineBlack,
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.CenterStart)
                .offset(y = (-30).dp)
                .alpha(starsAlpha)

        )
    }
}
