package com.yasunov.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yasunov.designsystem.theme.ShiftAppInternTheme

const val MATERIAL_TOP_BAR = 24
const val BOTTOM_BAR_PADDING = 56
@Composable
fun ShiftScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,

) {
    Scaffold(
        contentWindowInsets = WindowInsets(left = 0, top = MATERIAL_TOP_BAR, right = 0, bottom = BOTTOM_BAR_PADDING),
        modifier = modifier,
        scaffoldState = rememberScaffoldState(),
        topBar = topBar,
        drawerBackgroundColor = ShiftAppInternTheme.colors.uiBackground,
        drawerContentColor = ShiftAppInternTheme.colors.uiBackground,
        drawerScrimColor = ShiftAppInternTheme.colors.uiBackground,
        backgroundColor = ShiftAppInternTheme.colors.uiBackground,
        contentColor = ShiftAppInternTheme.colors.uiBackground,
        bottomBar = bottomBar,

    ) { padding ->
        content(padding)

    }

}