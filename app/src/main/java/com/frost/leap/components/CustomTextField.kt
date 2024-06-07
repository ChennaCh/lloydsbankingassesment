package com.frost.leap.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frost.leap.ui.theme.AceOnlineTheme
import com.frost.leap.ui.theme.Blue
import com.frost.leap.ui.theme.L_TEXT_COLOR_LIGHT
import com.frost.leap.ui.theme.Text_Filed_Box_Background_Color_Error
import com.frost.leap.utils.FontFamilyClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    testTag: String,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Enter text",
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    showError: Boolean,
    mobileErrorText: String,
    onImeActionPerformed: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color
) {

    /*
    value: Represents the current value of the text field.
    onValueChange: A callback function that is triggered whenever the user changes the text. It takes the updated text value as a parameter.
    modifier: Allows you to apply layout and styling modifications to the TextField. It has a default value of Modifier.
    label: An optional label that is displayed above the text field. It has a default value of "Enter text".
    */

    val customShape = RoundedCornerShape(8.dp) // Increase the corner radius
    var isFocused by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(0) }
    textFieldValue = value.length

    val customColors = TextFieldDefaults.textFieldColors(
        containerColor = if (showError) Text_Filed_Box_Background_Color_Error else containerColor, // Change the container color
        focusedLabelColor = Blue, // Change this to your desired focused label color
        unfocusedLabelColor = L_TEXT_COLOR_LIGHT, // Change this to your desired unfocused label color
        focusedIndicatorColor = Blue, // Change this to your desired focused border line color
        unfocusedIndicatorColor = containerColor, // Change this to your desired unfocused border line color
        cursorColor = L_TEXT_COLOR_LIGHT, //Change the cursor color
        errorCursorColor = Color.Red,
        errorLabelColor = Color.Red,
        errorSupportingTextColor = Color.Red
    )

    val customTextStyle = TextStyle(
        color = Color.Black, // Change the text color
        fontSize = 16.sp, // Change the font size
        fontWeight = FontWeight.Medium, // Change the font weight
        fontFamily = FontFamilyClass.robotoFamily,
        letterSpacing = 0.1.sp
    )

    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            isError = showError,
            label = {
                Text(
                    text = label,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag(testTag)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                autoCorrect = false,
                imeAction = imeAction,
                /*
                * Please check the below Examples
                * Words - Chenna Rao (Starting of every word is Caps)
                * Sentences - Chenna rao (Starting letter Caps)
                * Characters - CHENNA RAO (All Caps)
                * */
                capitalization = KeyboardCapitalization.Words
            ),
            keyboardActions = KeyboardActions(onDone = { onImeActionPerformed() }),
            textStyle = customTextStyle,
            colors = customColors,
            shape = customShape,
        )
        if (mobileErrorText.isNotEmpty()) {
            Text(
                text = mobileErrorText,
                modifier = Modifier.padding(start = 0.dp, top = 2.dp),
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                fontFamily = FontFamilyClass.robotoFamily,
                fontWeight = FontWeight.Normal,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTextFiledPreview() {
    AceOnlineTheme {
        CustomTextField(
            testTag = "test",
            "",
            onValueChange = {},
            "Enter full name",
            keyboardType = KeyboardType.Text,
            ImeAction.Next, // Set the desired input type here
            showError = false,
            mobileErrorText = "",
            onImeActionPerformed = {

            },
            containerColor = Color.White
        )
    }
}