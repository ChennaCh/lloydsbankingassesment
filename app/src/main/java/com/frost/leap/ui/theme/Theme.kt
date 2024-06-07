package com.frost.leap.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,

//     Other default colors to override
    background = Color(0xFF000000),
    surface = Color(0xFFFFFFFF),

    onPrimary = Color(0xDEFFFFFF),
    onSecondary = Color(0x99FFFFFF),
    onTertiary = Color(0x61FFFFFF),

    onBackground = Color(0xFF000000),
    onSurface = Color(0xFF000000),


    /* primary = md_theme_dark_primary,
     onPrimary = md_theme_dark_onPrimary,
     primaryContainer = md_theme_dark_primaryContainer,
     onPrimaryContainer = md_theme_dark_onPrimaryContainer,
     secondary = md_theme_dark_secondary,
     onSecondary = md_theme_dark_onSecondary,
     secondaryContainer = md_theme_dark_secondaryContainer,
     onSecondaryContainer = md_theme_dark_onSecondaryContainer,
     tertiary = md_theme_dark_tertiary,
     onTertiary = md_theme_dark_onTertiary,
     tertiaryContainer = md_theme_dark_tertiaryContainer,
     onTertiaryContainer = md_theme_dark_onTertiaryContainer,
     error = md_theme_dark_error,
     errorContainer = md_theme_dark_errorContainer,
     onError = md_theme_dark_onError,
     onErrorContainer = md_theme_dark_onErrorContainer,
     background = md_theme_dark_background,
     onBackground = md_theme_dark_onBackground,
     surface = md_theme_dark_surface,
     onSurface = md_theme_dark_onSurface,
     surfaceVariant = md_theme_dark_surfaceVariant,
     onSurfaceVariant = md_theme_dark_onSurfaceVariant,
     outline = md_theme_dark_outline,
     inverseOnSurface = md_theme_dark_inverseOnSurface,
     inverseSurface = md_theme_dark_inverseSurface,
     inversePrimary = md_theme_dark_inversePrimary,
     surfaceTint = md_theme_dark_surfaceTint,
     outlineVariant = md_theme_dark_outlineVariant,
     scrim = md_theme_dark_scrim,*/

    /*
    Background colors
     Primary color displayed most frequently across your app's screens and components;
     Primary Variant color is used to distinguish elements using primary colors, such as top app bar and the system bar.
     Secondary color provides more ways to accent and distinguish your product. Having a secondary color is optional, and should be applied sparingly to accent select parts of your UI;
     Secondary Variant color is used to distinguish elements using secondary colours;
     Background color appears behind scrollable content;
     Surface color uses on surfaces of components, like cards and menus;
     Error color used for indicating an error.
     */

    /*
    Typography and icon colors
    On Primary color of text and icons displayed on top of the primary color.
    On Secondary color of text and icons displayed on top of the secondary color;
    On Background color of text and icons displayed on top of the background color;
    On Surface color of text and icons displayed on top of the surface color;
    On Error color of text and icons displayed on top of the error color.*/
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,

//     Other default colors to override
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF5F5F5),

    onPrimary = Color(0xFF2A2A2A),
    onSecondary = Color(0xFF727272),
    onTertiary = Color(0xFFB0B0B0),

    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
)

@Composable
fun AceOnlineTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        val systemUiController: SystemUiController = rememberSystemUiController()
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Black.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                false
        }
//        systemUiController.isStatusBarVisible = false // Status bar
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}