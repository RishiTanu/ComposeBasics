package com.example.composebasics

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.composebasics.all_screen_size_support.AllScreenSizeSupport
import com.example.composebasics.bottom_navigation_badges.BottomNavViewBadges
import com.example.composebasics.compose_new_navigation.ComposeDefaultNav
import com.example.composebasics.compose_new_navigation.ComposeNavUsingDestinationLib
import com.example.composebasics.multi_select_lazycolumn.MultiSelectionLazyGrid
import com.example.composebasics.pagination.PaginationCompose
import com.example.composebasics.permission_handling.PermissionHandle
import com.example.composebasics.ui.theme.ComposeBasicsTheme
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    PaginationCompose()
                }
            }
        }
    }
}

@Composable
fun Progress() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressBar(
            percentage = 0.8f,
            number = 100
        )
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 3000,
    animDelay: Int = 0,
) {
    var animatedPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercentage = animateFloatAsState(
        targetValue = if (animatedPlayed) {
            percentage
        } else 0f,
        animationSpec = tween(
            durationMillis = animDuration
        )
    )
    LaunchedEffect(key1 = true) {
        animatedPlayed = true
    }

    Box(modifier = Modifier.size(radius * 2f), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                -90f,
                360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (currentPercentage.value * number).toInt().toString(),
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }
}


@Composable
fun simpleAnimation() {
    var sizeState by remember {
        mutableStateOf(200.dp)
    }
    val size by animateDpAsState(
        targetValue = sizeState,
        /*tween(
            durationMillis = 3000,
            delayMillis = 3000,
            easing = LinearOutSlowInEasing
        )*/
        spring(
            Spring.DampingRatioNoBouncy
        )
    )
    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeState += 50.dp
        }) {
            Text(text = "Increase Size")
        }
    }
}

@Composable
fun sideEffects() {
    var text by remember {
        mutableStateOf("0")
    }
    ComposeBasicsTheme {
        Button(onClick = {
            text += "#"
        }) {
            // i++
            Text(text = text)
        }
    }
}

@Composable
fun constraintLayout() {
    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")
        val guideLine = createGuidelineFromTop(0.5f)

        constrain(greenBox) {
            //  top.linkTo(parent.top)
            top.linkTo(guideLine)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        constrain(redBox) {
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            //   width = Dimension.fillToConstraints
            height = Dimension.value(100.dp)
        }
        createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)
    }
    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .background(Color.Green)
                .layoutId("greenBox")
        )
        Box(
            Modifier
                .background(Color.Red)
                .layoutId("redBox")
        )
    }
}


@Composable
fun lazyColumns() {
    LazyColumn {
        items(1000) {
            Text(
                text = "Text${it}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
        }
    }
}

@Composable
fun columnScroll() {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        for (i in 1..50) {
            Text(
                text = "Text${i}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
        }
    }
}

@Composable
fun ColorChangeState() {
    val colors = remember {
        mutableStateOf(Color.Green)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ColorBox(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            colors.value = it
        }
        Box(
            Modifier
                .weight(1f)
                .background(colors.value)
                .fillMaxSize()
        )
    }
}

@Composable
fun textFiledSnackBar() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var textFieldState by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        } // Add this to display Snackbar
    ) { paddingValue ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .padding(paddingValue)
        ) {
            TextField(
                value = textFieldState,
                onValueChange = {
                    textFieldState = it
                },
                label = {
                    Text(text = "Enter your name")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "Hello ${textFieldState}"
                    )
                }
            }) {
                Text(text = "Please greet me")
            }
        }
    }
}

@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit,
) {
    /* val colors = remember {
         mutableStateOf(Color.Green)
     }*/

    Box(modifier = modifier
        //.background(colors.value)
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
            /*colors.value =
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )*/
        }) {

    }
}

@Composable
fun ImageCard() {
    val painter = painterResource(id = R.drawable.appstore)
    val description = "kermit playing in the snow"
    val title = "kermit playing in the snow"

    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(15.dp)
    ) {
        ImageCard(painter = painter, contentDescription = description, title = title)
    }
}

@Composable
fun fontsText() {
    val fontFamily = FontFamily(
        Font(R.font.firasans_bold, FontWeight.Thin),
        Font(R.font.firasans_boldltalic, FontWeight.Thin)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Red,
                        fontSize = 50.sp
                    )
                ) {
                    append("J")
                }
                append("etpack ")
                withStyle(
                    style = SpanStyle(
                        color = Color.Red,
                        fontSize = 50.sp
                    )
                ) {
                    append("C")
                }
                append("ompose")
            },
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        ),
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}

@Composable
fun Series1() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Start")
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Start")
    }

    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .padding(16.dp)
            .border(5.dp, color = Color.Red)
            .padding(5.dp)
            .border(5.dp, color = Color.Blue)
            .padding(5.dp)
            .border(5.dp, color = Color.DarkGray)
            .padding(15.dp)
        //.width(600.dp)
        //.requiredWidth(600.dp)
    ) {

        Text(
            text = "Hello", modifier = Modifier
                .border(5.dp, Color.Yellow)
                .padding(5.dp)
                .offset(10.dp, 10.dp)
                .border(10.dp, Color.Blue)
                .padding(10.dp)
                .clickable {

                }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "World")
    }
}
