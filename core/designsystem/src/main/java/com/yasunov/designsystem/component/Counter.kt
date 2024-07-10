package com.yasunov.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography


@Composable
fun Counter(
    valueHandler: (Int) -> Unit,
    initialValue: Int,
    minValue: Int,
    maxValue: Int,
    modifier: Modifier = Modifier,
    ) {
    var counter by rememberSaveable { mutableIntStateOf(initialValue) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(
                color = ShiftAppInternTheme.colors.secondary,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        Text(
            "-",
            style = Typography.body2,
            color = ShiftAppInternTheme.colors.bodyPrimaryText,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                if (counter in (minValue + 1)..<(maxValue + 1)) {
                    counter--
                    valueHandler(counter)
                }
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            counter.toString(),
            style = Typography.body2,
            color = ShiftAppInternTheme.colors.bodyPrimaryText
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            "+",
            style = Typography.body2,
            color = ShiftAppInternTheme.colors.bodyPrimaryText,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                if (counter in (minValue - 1)..<maxValue) {
                    counter++
                    valueHandler(counter)
                }
            }
        )
    }

}


@Preview
@Composable
private fun CounterPreview() {
    ShiftAppInternTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.White)
                .fillMaxSize()
        ) {
            Counter(valueHandler = {}, initialValue = 1, minValue = 0, maxValue = 8)
        }
    }
}