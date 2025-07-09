package com.example.caffeine.screen.homeselectcoffeeorder

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeine.R
import com.example.caffeine.screen.drawShadowUnderCircle
import com.example.caffeine.ui.theme.CaffeineBlack
import com.example.caffeine.ui.theme.CaffeineWhite
import com.example.caffeine.ui.theme.sniglet_normal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun HomeSelectCoffeeOrderScreen(
    coffeeTypeTitle: String,
    onBackClick: () -> Unit,
    viewModel: HomeSelectCoffeeOrderViewModel,
    onContinueClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    HomeSelectCoffeeOrderContent(
        coffeeTypeTitle,
        onBackClick,
        uiState,
        viewModel::updateSelectedCupSize,
        viewModel::updateSelectedCaffeineSize,
        viewModel::hideTopBar,
        onContinueClick
    )
}

@Composable
private fun HomeSelectCoffeeOrderContent(
    coffeeTypeTitle: String,
    onBackClick: () -> Unit,
    uiState: HomeSelectCoffeeOrderUiState,
    updateSelectedCupSize: (CupSize) -> Unit,
    updateSelectedCaffeineSize: (CaffeineSize) -> Unit,
    hideTopBar: () -> Unit,
    onContinueClick: (Int) -> Unit
) {
    val cupHigh by animateDpAsState(
        targetValue = when (uiState.selectedCupSize) {
            CupSize.SMALL -> 188.dp
            CupSize.MEDIUM -> 244.dp
            CupSize.LARGE -> 300.dp
        }, label = "", animationSpec = tween(500)
    )
    val cupWidth by animateDpAsState(
        targetValue = when (uiState.selectedCupSize) {
            CupSize.SMALL -> 154.dp
            CupSize.MEDIUM -> 200.dp
            CupSize.LARGE -> 246.dp
        }, label = "", animationSpec = tween(500)
    )
    val screenHeight =
        with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.toPx().toDp() }
    val coffeeOffsetAnim = remember { Animatable(-screenHeight.value) }
    var previousSize by remember { mutableStateOf(uiState.selectedCaffeineSize) }
    LaunchedEffect(uiState.selectedCaffeineSize) {
        if (uiState.selectedCaffeineSize != previousSize) {
            val goingDown = uiState.selectedCaffeineSize > previousSize
            if (goingDown) {
                coffeeOffsetAnim.snapTo(-screenHeight.value)
                coffeeOffsetAnim.animateTo(
                    targetValue = (cupHigh * 0.5f).value,
                    animationSpec = tween(durationMillis = 1500)
                )
            } else {
                coffeeOffsetAnim.snapTo((cupHigh * 0.5f).value)
                coffeeOffsetAnim.animateTo(
                    targetValue = -screenHeight.value * 0.9f,
                    animationSpec = tween(durationMillis = 1500)
                )
            }
            previousSize = uiState.selectedCaffeineSize
        }
    }
    val coffeeSize by animateDpAsState(
        targetValue = when (uiState.selectedCupSize) {
            CupSize.SMALL -> 98.dp
            CupSize.MEDIUM -> 125.dp
            CupSize.LARGE -> 140.dp
        }, animationSpec = tween(500)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(
                state = rememberScrollState(), enabled = true
            )
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedVisibility(
            visible = uiState.isTopBarVisible,
            exit = slideOutVertically(tween(300), targetOffsetY = { -it }),
            enter = fadeIn(tween(10000)),
        ) {
            TopBarBack(
                title = coffeeTypeTitle,
                onBackClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 65.dp)
                    .padding(bottom = 16.dp)
            )
        }
        Spacer(Modifier.weight(1f))
        CupWithAnimationSection(
            uiState = uiState,
            coffeeSize = coffeeSize,
            coffeeOffsetAnim = coffeeOffsetAnim.value,
            cupHigh = cupHigh,
            cupWidth = cupWidth
        )
        CupSizeSelectorSection(
            uiState = uiState,
            updateSelectedCupSize = updateSelectedCupSize,
        )

        CoffeeSizeSelectorSection(
            uiState = uiState,
            updateSelectedCaffeineSize = updateSelectedCaffeineSize,
        )

        Spacer(Modifier.weight(1f))
        ButtonContinueSection(
            onContinueClick = onContinueClick, uiState = uiState, hideTopBar = hideTopBar
        )
    }
}

@Composable
private fun ButtonContinueSection(
    onContinueClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    uiState: HomeSelectCoffeeOrderUiState,
    hideTopBar: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Button(
        modifier = modifier
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
        ), onClick = {
            coroutineScope.launch(Dispatchers.Main) {
                hideTopBar()
                delay(700)
                hideTopBar()
                onContinueClick(uiState.selectedCupSize.size)
            }
        }) {
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

@Composable
private fun CoffeeSizeSelectorSection(
    uiState: HomeSelectCoffeeOrderUiState,
    updateSelectedCaffeineSize: (CaffeineSize) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 16.dp)
            .defaultMinSize(
                minWidth = 152.dp, minHeight = 56.dp
            )
            .background(color = Color(0xFFF5F5F5), shape = CircleShape)
            .padding(8.dp)
    ) {
        SelectorOption(
            onClick = {
                updateSelectedCaffeineSize(CaffeineSize.LOW)
            },
            text = "",
            modifier = Modifier.align(Alignment.CenterStart),
            selected = uiState.selectedCaffeineSize == CaffeineSize.LOW,
            hasIcon = true
        )
        SelectorOption(
            onClick = {
                updateSelectedCaffeineSize(CaffeineSize.MEDIUM)
            },
            text = "",
            selected = uiState.selectedCaffeineSize == CaffeineSize.MEDIUM,
            modifier = Modifier.align(Alignment.Center),
            hasIcon = true
        )
        SelectorOption(
            onClick = {
                updateSelectedCaffeineSize(CaffeineSize.HIGH)
            },
            text = "",
            selected = uiState.selectedCaffeineSize == CaffeineSize.HIGH,
            modifier = Modifier.align(Alignment.CenterEnd),
            hasIcon = true
        )
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 2.dp)
            .width(152.dp)
    ) {
        Text(
            text = "Low",
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W500,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.25.sp,
            color = Color(0x991F1F1F),
        )
        Text(
            text = "Medium",
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W500,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.25.sp,
            color = Color(0x991F1F1F),
        )
        Text(
            text = "High",
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W500,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.25.sp,
            color = Color(0x991F1F1F),
        )
    }
}

@Composable
private fun CupSizeSelectorSection(
    uiState: HomeSelectCoffeeOrderUiState,
    updateSelectedCupSize: (CupSize) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .defaultMinSize(
                minWidth = 152.dp, minHeight = 56.dp
            )
            .background(color = Color(0xFFF5F5F5), shape = CircleShape)
            .padding(8.dp)
    ) {
        SelectorOption(
            onClick = {
                updateSelectedCupSize(CupSize.SMALL)
            },
            text = "S",
            modifier = Modifier.align(Alignment.CenterStart),
            selected = uiState.selectedCupSize == CupSize.SMALL,
        )
        SelectorOption(
            onClick = {
                updateSelectedCupSize(CupSize.MEDIUM)
            },
            text = "M",
            selected = uiState.selectedCupSize == CupSize.MEDIUM,
            modifier = Modifier.align(Alignment.Center)
        )
        SelectorOption(
            onClick = {
                updateSelectedCupSize(CupSize.LARGE)
            },
            text = "L",
            selected = uiState.selectedCupSize == CupSize.LARGE,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
private fun CupWithAnimationSection(
    uiState: HomeSelectCoffeeOrderUiState,
    coffeeSize: Dp,
    coffeeOffsetAnim: Float,
    cupHigh: Dp,
    cupWidth: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(342.dp)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = uiState.selectedCupSize.size.toString() + " ML",
            textAlign = TextAlign.Start,
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.25.sp,
            color = Color(0x99000000),
            modifier = Modifier.padding(top = 64.dp, end = 16.dp)
        )
        Image(
            painter = painterResource(R.drawable.coffee),
            contentDescription = null,
            modifier = Modifier
                .size(coffeeSize)
                .align(Alignment.TopCenter)
                .offset(y = coffeeOffsetAnim.dp)

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
                    .align(Alignment.Center)
                    .size(cupWidth * 0.3f)
            )
        }
    }
}

@Composable
private fun TopBarBack(
    modifier: Modifier = Modifier, title: String, onBackClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(start = 16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(end = 12.dp)
                .size(48.dp)
                .clip(CircleShape)
                .background(color = Color(0xFFF5F5F5))
                .clickable(onClick = onBackClick)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_right_04),
                contentDescription = null,
                tint = CaffeineBlack,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }
        Text(
            text = title,
            textAlign = TextAlign.Start,
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W700,
            fontSize = 24.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.25.sp,
            color = CaffeineBlack
        )
    }
}

@Composable
private fun SizeText(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    textColor: Color = Color(0x991F1F1F)
) {
    Box(
        modifier = modifier
            .defaultMinSize(
                minWidth = 40.dp, minHeight = 40.dp
            )
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
    ) {
        Text(
            text = text,
            fontFamily = sniglet_normal,
            fontWeight = FontWeight.W700,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.25.sp,
            color = textColor,
            modifier = Modifier.align(Alignment.Center)

        )
    }
}

@Composable
private fun SelectorOption(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    selected: Boolean = false,
    hasIcon: Boolean = false,
) {
    Box(modifier) {
        AnimatedVisibility(
            visible = selected,
            enter = fadeIn(tween(500)),
            exit = fadeOut(tween(1000)),
            modifier = Modifier.drawShadowUnderCircle(
                    shadowColor = "#80B94B23",
                    shadowRadius = 40f,
                    leftOffset = 60f,
                    topOffset = 0f,
                    rightOffset = 60f,
                    bottomOffset = 50f
                )
        ) {
            Box(
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = 40.dp, minHeight = 40.dp
                    )
                    .clip(CircleShape)
                    .background(color = Color(0xFF7C351B))
            ) {
                if (hasIcon) {
                    Icon(
                        painter = painterResource(R.drawable.coffee_beans_ic),
                        contentDescription = null,
                        tint = CaffeineWhite,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        SizeText(
            text = text,
            onClick = onClick,
            textColor = if (selected) Color.White else Color(0x991F1F1F)
        )
    }
}
















