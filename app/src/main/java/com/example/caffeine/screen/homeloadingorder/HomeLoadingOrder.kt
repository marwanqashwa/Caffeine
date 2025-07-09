package com.example.caffeine.screen.homeloadingorder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeine.R
import com.example.caffeine.composable.LineLoading
import com.example.caffeine.screen.homeselectcoffeeorder.CupSize
import com.example.caffeine.ui.theme.sniglet_extra_bold
import com.example.caffeine.ui.theme.sniglet_normal
import kotlinx.coroutines.delay

@Composable
fun HomeLoadingOrder(
    cupSize: Int, onLoadingComplete: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(5000)
        onLoadingComplete()
    }
    val selectedCupSize = when (cupSize) {
        150 -> CupSize.SMALL
        200 -> CupSize.MEDIUM
        else -> CupSize.LARGE
    }

    val cupHigh = when (selectedCupSize) {
        CupSize.SMALL -> 188.dp
        CupSize.MEDIUM -> 244.dp
        CupSize.LARGE -> 300.dp
    }
    val cupWidth = when (selectedCupSize) {
        CupSize.SMALL -> 154.dp
        CupSize.MEDIUM -> 200.dp
        CupSize.LARGE -> 246.dp
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .verticalScroll(
                state = rememberScrollState(), enabled = true
            )
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CupSection(
            title = selectedCupSize.size.toString() + " ML",
            cupHigh = cupHigh,
            cupWidth = cupWidth,
            modifier = Modifier.padding(top = 64.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        LineLoading(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        )
        Text(
            text = "Almost Done",
            textAlign = TextAlign.Start,
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W700,
            fontSize = 22.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.25.sp,
            color = Color(0xDE1F1F1F),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Your coffee will be finish in",
            textAlign = TextAlign.Start,
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            color = Color(0x991F1F1F),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "CO",
                textAlign = TextAlign.Start,
                fontFamily = sniglet_extra_bold,
                fontWeight = FontWeight.W800,
                fontSize = 32.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xFF7C351B),
            )
            TextSeperator()
            Text(
                text = "FF",
                textAlign = TextAlign.Start,
                fontFamily = sniglet_extra_bold,
                fontWeight = FontWeight.W800,
                fontSize = 32.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xFF7C351B),
            )
            TextSeperator(
            )
            Text(
                text = "EE",
                textAlign = TextAlign.Start,
                fontFamily = sniglet_extra_bold,
                fontWeight = FontWeight.W800,
                fontSize = 32.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xFF7C351B),
            )

        }
    }


}

@Composable
fun TextSeperator(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp), modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(4.dp)
                .background(Color(0x1F1F1F1F), shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(4.dp)
                .background(Color(0x1F1F1F1F), shape = CircleShape)
        )
    }
}

@Composable
private fun CupSection(
    title: String, cupHigh: Dp, cupWidth: Dp, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(342.dp)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Start,
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.25.sp,
            color = Color(0x99000000),
            modifier = Modifier.padding(top = 64.dp, end = 16.dp)
        )

        Box(
            modifier = Modifier
                .size(cupWidth, cupHigh)
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(R.drawable.empty_cup),
                contentDescription = null,
                modifier = Modifier
                    .size(cupWidth, cupHigh)
                    .align(Alignment.Center)
            )
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center)
            )
        }


    }
}
