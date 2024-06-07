package com.frost.leap.screens.otp

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.frost.leap.R
import com.frost.leap.activities.MainActivity
import com.frost.leap.components.BackHandler
import com.frost.leap.components.CustomButton
import com.frost.leap.ui.theme.AceOnlineTheme
import com.frost.leap.ui.theme.Blue
import com.frost.leap.ui.theme.L_TEXT_COLOR_MEDIUM
import com.frost.leap.ui.theme.Typography
import com.frost.leap.ui.theme.White
import com.frost.leap.utils.Constants
import com.frost.leap.utils.Utility

/**
 * Created by Chenna Rao on 26/09/23.
 * <p>
 * Frost Interactive
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OTPScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    MainActivity.currentDestinationSplashActivity = Constants.NavigationStrings.OTP_NAVIGATION
    val statusBarHeight = Utility.getStatusBarHeight(context = context)

    var buttonEnabled by remember { mutableStateOf(false) }
    var showLoader by remember { mutableStateOf(false) }
    var otpValue by remember {
        mutableStateOf("")
    }

    //OnBackPressed
    BackHandler(onBackPressed = {
        navController.navigateUp()
    })

    Surface(
        color = White,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = statusBarHeight)
        ) {
            // Add the Image composable to the background
            Image(
                painter = painterResource(id = R.drawable.ace_online_back_cover),
                contentDescription = null, // Provide a meaningful description if needed
                modifier = Modifier.fillMaxSize(), // Fill the entire available space
                contentScale = ContentScale.FillBounds // Scale the image to fill the bounds
            )

            Column {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f)
                        .padding(top = 92.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_deep_learn),
                        contentDescription = null, // Provide a meaningful description if needed
                        modifier = Modifier
                            .width(160.dp)
                            .height(48.dp),
                    )
                    Text(
                        text = "#LetsACEit!",
                        style = Typography.displayMedium,
                        textAlign = TextAlign.Start,
                        color = Blue,
                        modifier = Modifier
                            .padding(top = 20.dp),
                    )
                }
                Column(
                    modifier = Modifier
                        .background(
                            color = White
                        )
                        .weight(1f)
                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Verification",
                        style = Typography.titleLarge,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF007AFF),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 0.dp)
                    )
                    Text(
                        text = "We have sent you an SMS with a code to your number",
                        style = Typography.titleSmall,
                        textAlign = TextAlign.Start,
                        color = L_TEXT_COLOR_MEDIUM,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp)
                    )

                    OtpTextField(
                        otpText = otpValue,
                        onOtpTextChange = { value, otpInputFilled ->
                            otpValue = value
                        }
                    )

                    Spacer(modifier = Modifier.height(0.dp))
                    CustomButton(
                        onClick = {
                            // Handle button click action
//                            if (mobileNumberString.length < 10) {
//                                showMobileError = true
//                                mobileErrorText = "Please enter valid mobile number"
//                                showLoader = !showLoader
//
//                            } else {
//                                focusManager.clearFocus()
//                                buttonClicked = true
//                                mobileNumber = OnBoardingData.globalMobileNumber
//                                retryAttempt++
//                                coroutineScope.launch {
//                                    navController.navigate(Screen.OTPPage.route)
//                                }
//
//                            }
                        },
                        text = "VERIFY",
                        enabled = buttonEnabled,
                        showLoader = showLoader
                    )

                    Row(
                        modifier = modifier
                            .padding(top = 4.dp, start = 8.dp)
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Didn't receive the OTP?",
                            style = Typography.titleSmall,
                            textAlign = TextAlign.Start,
                            color = L_TEXT_COLOR_MEDIUM,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "RESEND CODE",
                            style = Typography.titleMedium,
                            textAlign = TextAlign.Start,
                            color = Blue,
                            modifier=Modifier.clickable {
                                Toast.makeText(context, "Resend", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, group = "OTPScreen")
@Composable
fun OTPScreenPreview() {
    AceOnlineTheme {
        OTPScreen(
            navController = rememberNavController()
        )
    }
}