package com.frost.leap.screens.otp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frost.leap.ui.theme.AceOnlineTheme
import com.frost.leap.ui.theme.Blue
import com.frost.leap.ui.theme.L_TEXT_COLOR_PRIMARY
import com.frost.leap.ui.theme.OTP_FIELD_BG_COLOR
import com.frost.leap.utils.FontFamilyClass

/**
 * Created by Chenna Rao on 27/09/23.
 * <p>
 * Frost Interactive
 */
@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText
                    )

                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(55.dp)
            .height(58.dp)
            .border(
                2.dp, when {
                    isFocused -> Blue
                    else -> OTP_FIELD_BG_COLOR
                }, RoundedCornerShape(4.dp)
            )
            .padding(top = 12.dp, bottom = 12.dp),
        text = char,
        fontFamily = FontFamilyClass.robotoFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        color = L_TEXT_COLOR_PRIMARY,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true, group = "OTPTextField")
@Composable
fun OTPTextFieldPreview() {
    AceOnlineTheme {
        OtpTextField(
            otpText = "",
            onOtpTextChange = { value, otpInputFilled ->

            }
        )
    }
}