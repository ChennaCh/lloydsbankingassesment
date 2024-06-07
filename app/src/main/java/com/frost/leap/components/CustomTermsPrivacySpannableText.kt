package com.frost.leap.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.frost.leap.utils.FontFamilyClass

@Composable
fun TermsPrivacySpannableText(
    appendString: String,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit
) {
    val terms = "Terms of Use"
    val privacy = "Privacy Policy."
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamilyClass.robotoFamily,
                letterSpacing = 0.03.sp,
                fontSize = 12.sp,
            )
        ) {
            append(appendString)
        }
        append(" ")
        val termsStart = length
        withStyle(
            style = SpanStyle(
                color = Color(0xFF007AFF),
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamilyClass.robotoFamily,
                letterSpacing = 0.03.sp,
                fontSize = 12.sp,
            )
        ) {
            append(terms)
            addStringAnnotation("TermsClickable", "", termsStart, length)
        }
        append(" & ")
        val privacyStart = length
        withStyle(
            style = SpanStyle(
                color = Color(0xFF007AFF),
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamilyClass.robotoFamily,
                letterSpacing = 0.03.sp,
                fontSize = 12.sp,
            )
        ) {
            append(privacy)
            addStringAnnotation("PrivacyClickable", "", privacyStart, length)
        }
    }



    Column {
        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                annotatedText.getStringAnnotations(
                    tag = "TermsClickable",
                    start = 0,
                    end = annotatedText.length
                ).firstOrNull { it.start <= offset && offset <= it.end }?.let {
                    onTermsClick()
                }

                annotatedText.getStringAnnotations(
                    tag = "PrivacyClickable",
                    start = 0,
                    end = annotatedText.length
                ).firstOrNull { it.start <= offset && offset <= it.end }?.let {
                    onPrivacyClick()
                }
            }
        )

    }
}