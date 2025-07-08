package com.example.caffeine.screen.homeshowcoffeetypes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.example.caffeine.CoffeeType
import com.example.caffeine.R
import com.example.caffeine.coffeeTypes
import com.example.caffeine.composable.TopBar
import com.example.caffeine.ui.theme.CaffeineBlack
import com.example.caffeine.ui.theme.CaffeineWhite
import com.example.caffeine.ui.theme.sniglet_normal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun HomeShowCoffeeTypes(
    onContinueClick: (coffeeTypeTitle: String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    var isNavigate by remember { mutableStateOf(false) }

    val coffeeTypes = coffeeTypes
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CaffeineWhite)
            .verticalScroll(
                state = rememberScrollState(), enabled = true
            )
            .padding(vertical = 16.dp), horizontalAlignment = Alignment.Start
    ) {

        AnimatedVisibility(
            visible = !isNavigate,
            exit = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(400)),
        ) {
            TopBar(
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        AnimatedVisibility(
            visible = !isNavigate,
            exit = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(400)),
        ) {
            WelcomeSection()
        }

        Spacer(modifier = Modifier.weight(1f))
        val pagerState = rememberPagerState(pageCount = { coffeeTypes.size })
        AnimatedVisibility(
            visible = !isNavigate,
            exit = fadeOut(animationSpec = tween(1000)),
        ) {
            CoffeeTypesPager(
                modifier = Modifier.padding(bottom = 16.dp),
                pagerState = pagerState,
                coffeeTypes = coffeeTypes
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        AnimatedVisibility(
            visible = !isNavigate,
            exit = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(400)),
            modifier = Modifier
                .defaultMinSize(minHeight = 56.dp)
                .padding(horizontal = 32.dp, vertical = 18.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    coroutineScope.launch(Dispatchers.Main) {
                        isNavigate = true
                        delay(500)
                        onContinueClick(coffeeTypes[pagerState.currentPage].name)
                    }
                },
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
                    ),
                colors = ButtonColors(
                    containerColor = CaffeineBlack,
                    contentColor = CaffeineWhite,
                    disabledContainerColor = CaffeineWhite,
                    disabledContentColor = CaffeineBlack
                )
            ) {
                Text(
                    text = "Continue",
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
}

@Composable
fun WelcomeSection() {
    Column {
        Text(
            text = "Good Morning",
            textAlign = TextAlign.Start,
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W700,
            fontSize = 36.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.25.sp,
            color = Color(0xFFB3B3B3),
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "Marwan ☀",
            textAlign = TextAlign.Start,
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W700,
            fontSize = 36.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.25.sp,
            color = Color(0xFF3B3B3B),
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "What would you like to drink today?",
            textAlign = TextAlign.Start,
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.25.sp,
            color = Color(0xCC1F1F1F),
            modifier = Modifier.padding(start = 16.dp, bottom = 36.dp)
        )
    }

}

@Composable
fun CoffeeTypesPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    coffeeTypes: List<CoffeeType>,
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 100.dp)
    ) { pageIndex ->
        val coffeeType = coffeeTypes[pageIndex]
        val progress =
            1f - pagerState.getOffsetDistanceInPages(pageIndex).absoluteValue.coerceIn(0f, 1f)
        val imageWidth by animateDpAsState(
            targetValue = lerp(120.dp, 200.dp, progress), label = ""
        )
        val imageHeight by animateDpAsState(
            targetValue = lerp(150.dp, 244.dp, progress), label = ""
        )
        Column(
            modifier = Modifier
                .defaultMinSize(minHeight = 298.dp)
                .width(199.dp)
                .offset(y = (progress * -3).dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(coffeeType.image),
                contentDescription = null,
                modifier = Modifier.size(
                    width = imageWidth, height = imageHeight
                )
            )
            Text(
                text = coffeeType.name,
                textAlign = TextAlign.Center,
                fontFamily = sniglet_normal,
                fontWeight = FontWeight.W700,
                fontSize = 32.sp,
                lineHeight = 35.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xFF1F1F1F),
                modifier = Modifier.padding(top = 16.dp)
            )

        }
    }
}