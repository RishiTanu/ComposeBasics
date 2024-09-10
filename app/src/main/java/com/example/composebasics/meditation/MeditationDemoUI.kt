package com.example.composebasics.meditation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasics.R
import com.example.composebasics.ui.theme.ButtonBlue
import com.example.composebasics.ui.theme.DarkerButtonBlue
import com.example.composebasics.ui.theme.DeepBlue
import com.example.composebasics.ui.theme.LightRed
import com.example.composebasics.ui.theme.TextWhite


@Composable
fun MeditationDemoUI() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ChipSection(listOf("Sweet", "Goods", "Sour"))
            DailyThoughts()
        }
    }
}

@Composable
fun FeaturedSection(feature: List<Feature>) {
    
}

@Composable
fun DailyThoughts(
    color: Color = LightRed,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 15.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Daily Thought",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Meditation * 3-10 min",
                color = TextWhite,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun ChipSection(
    chips: List<String>,
) {
    var selectedChip by remember {
        mutableStateOf(0)
    }

    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChip = it
                    }
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        if (selectedChip == it) {
                            ButtonBlue
                        } else {
                            DarkerButtonBlue
                        }
                    )
                    .padding(15.dp)
            ) {
                Text(
                    text = chips[it],
                    color = TextWhite,
                    fontSize = 14.sp
                )
            }
        }
    }

}

@Composable
fun GreetingSection(
    name: String = "Rishi",
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, $name",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "We wish you have a good day!",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}