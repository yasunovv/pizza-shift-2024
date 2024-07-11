package com.yasunov.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yasunov.designsystem.theme.ShiftAppInternTheme

@Composable
fun ShiftScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,

) {
    Scaffold(
        modifier = modifier.consumeWindowInsets(WindowInsets.safeContent),
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