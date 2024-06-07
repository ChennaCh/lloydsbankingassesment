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
fun MyClickableSpannableText(appendString: String, annotationString: String, onClick: () -> Unit) {
    val annotatedText = buildAnnotatedString {
        pushStringAnnotation("Clickable", annotation = annotationString)
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamilyClass.gilroyFamily,
                letterSpacing = 1.sp,
                fontSize = 14.sp,
            )
        ) {
            append(appendString)
        }
        append(" ")
        withStyle(
            style = SpanStyle(
                color = Color(0xFF1D4ED8),
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamilyClass.gilroyFamily,
                letterSpacing = 1.sp,
                fontSize = 14.sp,
            )
        ) {
            append(annotationString)
        }
        pop()
    }

    Column {
        ClickableText(

            text = annotatedText,
            onClick = { offset ->
                val annotations = annotatedText.getStringAnnotations(
                    tag = "Clickable",
                    start = offset,
                    end = offset
                )
                if (annotations.isNotEmpty()) {
                    onClick()
                }
            }
        )
    }
}