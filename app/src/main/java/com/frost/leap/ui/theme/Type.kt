package com.frost.leap.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.frost.leap.utils.FontFamilyClass

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamilyClass.robotoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamilyClass.robotoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 0.02.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamilyClass.robotoFamily,
        fontWeight = FontWeight(500),
        fontSize = 12.sp,
        letterSpacing = 0.02.sp
    ),
    displayLarge = TextStyle(
        fontFamily = FontFamilyClass.robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 1.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamilyClass.robotoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = 0.01.sp
    )
)