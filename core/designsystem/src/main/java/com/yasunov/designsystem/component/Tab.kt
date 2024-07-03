package com.yasunov.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.ShiftAppInternTheme.colors
import com.yasunov.designsystem.theme.Typography

@Composable
fun PizzaTab(tabTitles: List<String>, modifier: Modifier = Modifier) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = colors.secondary,
        contentColor = colors.bodyPrimaryText,
        indicator = {

        },
        divider = {},
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colors.secondary)
            .padding(2.dp)
    ) {
        tabTitles.forEachIndexed { index, text ->
            TabItem(text = text, selected = selectedTabIndex == index) {
                selectedTabIndex = index
            }
        }
    }

}

@Composable
private fun TabItem(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                color = if (selected) colors.light else colors.secondary,
                shape = RoundedCornerShape(14.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            style = Typography.body2,
            text = text,
            color = if (selected) colors.bodyPrimaryText else colors.tertiaryText
        )
    }
}

@Preview
@Composable
private fun PreviewTab() {
    ShiftAppInternTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
                .background(colors.light)
                .padding(horizontal = 24.dp)
        ) {
            PizzaTab(listOf("Маленькая", "Средняя", "Большая"), Modifier)

        }
    }

}
