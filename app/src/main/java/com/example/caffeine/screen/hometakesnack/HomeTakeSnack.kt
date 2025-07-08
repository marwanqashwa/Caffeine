package com.example.caffeine.screen.hometakesnack

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.fontscaling.MathUtils.lerp
import androidx.compose.ui.unit.sp
import com.example.caffeine.R
import com.example.caffeine.snacks
import com.example.caffeine.ui.theme.CaffeineBlack
import com.example.caffeine.ui.theme.CaffeineWhite
import com.example.caffeine.ui.theme.sniglet_normal
import kotlin.math.abs

@Composable
fun HomeTakeSnack(
    onCloseClick: () -> Unit, onSnackClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CaffeineWhite)
            .padding(top = 16.dp),
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
        Text(
            text = "Take your snack",
            textAlign = TextAlign.Start,
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W700,
            fontSize = 22.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.25.sp,
            color = Color(0xDE1F1F1F),
            modifier = Modifier
                .padding(bottom = 24.dp, start = 16.dp)
                .fillMaxWidth()
        )
        val pagerState = rememberPagerState(pageCount = { snacks.size })
        SnackPager(
            pagerState = pagerState, onSnackClick = onSnackClick, modifier = Modifier.weight(1f)
        )

    }
}


@SuppressLint("RestrictedApi")
@Composable
fun SnackPager(
    pagerState: PagerState, modifier: Modifier = Modifier, onSnackClick: (Int) -> Unit
) {

    val screenW = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp }
    val screenH = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp }

    VerticalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxSize()
            .offset(x = -screenW * 0.25f),
        contentPadding = PaddingValues(vertical = screenH * 0.25f),
        flingBehavior = PagerDefaults.flingBehavior(pagerState)
    ) { page ->
        val offset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
        val absOffset = abs(offset)

        val scale = 1f - when {
            absOffset < 0.001f -> 0f
            offset < -1f -> (0.1f * absOffset).coerceAtLeast(0.1f)
            offset < 0f -> 0.1f * absOffset
            else -> 0.2f * absOffset
        }

        val rotation = when {
            absOffset < 0.1f -> 2f
            offset < -1f -> lerp(4f, 8f, (offset + 1f) / -1f)
            else -> -8f * offset
        }

        val offsetX = when {
            offset < -1f -> lerp(
                screenW.value * -0.4f, screenW.value * -1.6f, (offset + 1f) / -1f
            )

            offset < 0f -> screenW.value * 0.4f * offset
            else -> -screenW.value * 0.5f * offset
        }

        val offsetY = when {
            offset < -1f -> lerp(
                screenH.value * -0.06f, screenH.value * -1f, (offset + 1f) / -1f
            )

            offset < 0f -> screenH.value * 0.1f * offset
            else -> screenH.value * 0.5f * offset
        }

        Box(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale * 1.2f
                    scaleY = scale * 1.2f
                    rotationZ = rotation
                    translationX = offsetX
                    translationY = offsetY
                }
                .size(260.dp, 274.dp).shadow(
                    elevation =20.dp,
                    spotColor = Color(0x1F000000).copy(alpha = 0.3f),
                    ambientColor = Color(0x1F000000).copy(alpha = 0.3f),
                    shape = RoundedCornerShape(32.dp),
                    clip = true
                )
                .background(Color(0xFFF5F5F5), RoundedCornerShape(32.dp))

                .clickable(
                    onClick = { onSnackClick(page) },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(snacks[page]),
                contentDescription = null,
                modifier = Modifier.size(screenW * 0.4f)
            )
        }
    }
}





