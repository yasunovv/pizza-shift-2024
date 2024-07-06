package com.yasunov.designsystem.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yasunov.designsystem.R
import com.yasunov.designsystem.model.ToppingCardModel
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.ShiftAppInternTheme.colors
import com.yasunov.designsystem.theme.Typography

@Composable
fun ToppingCard(
    toppingCard: ToppingCardModel,
    onClickCard: (ToppingCardModel, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isSelected by rememberSaveable { mutableStateOf(false) }
    val borderStroke: Dp by animateDpAsState(
        targetValue = if (isSelected) 2.dp else 0.dp,
        label = ""
    )
    val price = toppingCard.price
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        contentColor = colors.bodyPrimaryText,
        border = null,
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .border(width = borderStroke, color = if (borderStroke == 0.dp) Color.White else colors.brand, shape = RoundedCornerShape(16.dp))
            .clickable(interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    isSelected = !isSelected
                    onClickCard(toppingCard, isSelected)
                }),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val imageSrc = toppingCard.imageSrc
            AsyncImage(
                model = imageSrc,
                contentDescription = null,
                modifier = Modifier.size(88.dp)
                    .padding(top = 8.dp)
            )
            val name = toppingCard.name
            Text(
                name,
                textAlign = TextAlign.Center,
                style = Typography.caption, color = colors.titleText,
                maxLines = 1,

                )
            Text(
                text = stringResource(R.string.price, price),
                textAlign = TextAlign.Center,
                style = Typography.button,
                color = colors.titleText,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun IngredientCardPreview() {
    ShiftAppInternTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = ingredientCardObjList, key = { it.id }) {
                ToppingCard(
                    toppingCard = it,
                    onClickCard = { _, _ ->}
                )
            }
        }
    }
}


private val ingredientCardObjList = List(100) { id ->
    ToppingCardModel(
        id = id,
        price = 179,
        name = "Сырный бортик",
        imageSrc = "https://shift-backend.onrender.com/static/images/ingredient/mozzarella.png"
    )
}