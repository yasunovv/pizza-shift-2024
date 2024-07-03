package com.yasunov.designsystem.component

import androidx.compose.foundation.background
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yasunov.designsystem.model.IngredientCardModel
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.ShiftAppInternTheme.colors
import com.yasunov.designsystem.theme.Typography

@Composable
fun IngredientCard(
    ingredientCard: IngredientCardModel,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .padding(4.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
            )
            .background(Color.White)
            .padding(8.dp)
    ) {
        val imageSrc = ingredientCard.imageSrc
        AsyncImage(
            model = imageSrc,
            contentDescription = null,
            modifier = modifier.size(88.dp)
        )
        Text(
            ingredientCard.name,
            textAlign = TextAlign.Center,
            style = Typography.caption, color = colors.titleText
        )
        Text(
            "${ingredientCard.price} ₽",
            textAlign = TextAlign.Center,
            style = Typography.button,
            color = colors.titleText
        )
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
                IngredientCard(it)
            }


        }
    }
}


private val ingredientCardObjList = List(100) { id ->
    IngredientCardModel(
        id = id,
        price = 179,
        name = "Сырный бортик",
        imageSrc = "https://shift-backend.onrender.com/static/images/ingredient/mozzarella.png"
    )
}