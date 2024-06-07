package com.frost.leap.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frost.leap.ui.theme.AceOnlineTheme
import com.frost.leap.utils.FontFamilyClass


@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = false,
    showLoader: Boolean,
) {

    val textColor: Color = if (enabled) Color.White else Color(0xFF9A9A9A)
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF007AFF), // Active/Enabled color
        disabledContainerColor = Color(0xFFE2E2E2) // Inactive/Disabled color
    )


    Button(
        onClick = onClick,
        enabled = enabled, // Set the button initially disabled
        modifier = modifier
            .fillMaxWidth()
            .height(42.dp),
        colors = buttonColors,
        shape = RoundedCornerShape(5.dp)
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (!showLoader) {
                Text(
                    text = text,
                    fontFamily = FontFamilyClass.robotoFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = textColor
                )
            } else {
                LoadingAnimation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    AceOnlineTheme {
        CustomButton(
            onClick = {
                // Handle button click action
            },
            text = "Button",
            enabled = false,
            showLoader = false
        )
    }
}