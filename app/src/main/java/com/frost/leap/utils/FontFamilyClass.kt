package com.frost.leap.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.frost.leap.R

class FontFamilyClass {

    companion object {
        val poppinsFamily = FontFamily(
            Font(R.font.poppins_light, FontWeight.Light),
            Font(R.font.poppins_normal, FontWeight.Normal),
            Font(R.font.poppins_medium, FontWeight.Medium),
            Font(R.font.poppins_semibold, FontWeight.SemiBold),
            Font(R.font.poppins_bold, FontWeight.Bold)
        )
        val gilroyFamily = FontFamily(
            Font(R.font.gilroy_light, FontWeight.Light),
            Font(R.font.gilroy_normal, FontWeight.Normal),
            Font(R.font.gilroy_medium, FontWeight.Medium),
            Font(R.font.gilroy_semibold, FontWeight.SemiBold),
            Font(R.font.gilroy_bold, FontWeight.Bold)
        )
        val robotoFamily = FontFamily(
            Font(R.font.roboto_light, FontWeight.Light),
            Font(R.font.roboto_normal, FontWeight.Normal),
            Font(R.font.roboto_medium, FontWeight.Medium),
            Font(R.font.roboto_semibold, FontWeight.SemiBold),
            Font(R.font.roboto_bold, FontWeight.Bold)
        )
    }
}