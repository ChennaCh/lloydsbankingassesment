package com.frost.leap.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.frost.leap.R
import com.frost.leap.activities.MainActivity.Companion.currentDestinationSplashActivity
import com.frost.leap.activities.MainActivity.Companion.isSignIn
import com.frost.leap.architecture.onboarding.presentation.OnBoardingEvent
import com.frost.leap.architecture.onboarding.presentation.OnBoardingStateHolder
import com.frost.leap.architecture.onboarding.presentation.screens.SignInData.globalMobileNumber
import com.frost.leap.components.BackHandler
import com.frost.leap.components.CustomButton
import com.frost.leap.components.MyClickableSpannableText
import com.frost.leap.routing.Screen
import com.frost.leap.ui.theme.AceOnlineTheme
import com.frost.leap.utils.Constants
import com.frost.leap.utils.FontFamilyClass
import com.frost.leap.utils.Logger
import com.frost.leap.utils.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("InternalInsetResource")
@Composable
fun VerifyOTPScreen(
    navController: NavController,
    stateFlow: StateFlow<OnBoardingStateHolder?>,
    onEvent: ((OnBoardingEvent) -> Unit)?,
    modifier: Modifier = Modifier,
) {

    currentDestinationSplashActivity = Constants.NavigationStrings.VERIFY_OTP_NAVIGATION

    //OnBackPressed
    BackHandler(onBackPressed = {
        if (onEvent != null) {
            onEvent(OnBoardingEvent.RefreshState)
        }
        navController.navigateUp()
    })

    val coroutineScope = rememberCoroutineScope()
    val emptyInteractionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var buttonEnabled by remember { mutableStateOf(false) }
    var showLoader by remember { mutableStateOf(false) }
    val statusBarHeight = Utility.getStatusBarHeight(context = context)
    val buttonText: String
    val headingText: String
    var buttonClicked by remember { mutableStateOf(false) }
    var navigateToScreen by remember { mutableStateOf(false) }

    //OTPValues
    val fieldCount = 6
    val focusRequesters = remember { List(fieldCount) { FocusRequester() } }
    val textFieldsText = remember { mutableStateListOf(*Array(fieldCount) { "" }) }
    var otpErrorText by remember { mutableStateOf("") }
    var showOTPError by remember { mutableStateOf(false) }
    var otpNumber by remember { mutableStateOf("") }

    val state by stateFlow.collectAsState()

    val customColors = TextFieldDefaults.outlinedTextFieldColors(
        containerColor = Color.White, // Change the container color
        focusedBorderColor = Color(0XFF1D4ED8), // Change the outline color when focused
        unfocusedBorderColor = Color(0XFFD1D6E3), // Change the outline color when unfocused
        cursorColor = Color(0XFF1B1C1E),
        errorCursorColor = Color.Red,
        errorBorderColor = Color.Red,
        errorSupportingTextColor = Color.Red
    )
    val customTextStyle = TextStyle(
        color = Color.Black, // Change the text color
        fontSize = 20.sp, // Change the font size
        fontWeight = FontWeight.SemiBold, // Change the font weight
        fontFamily = FontFamilyClass.gilroyFamily,
        textAlign = TextAlign.Center
    )
    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }
    otpNumber = textFieldsText.joinToString(separator = "") { it }

    if (otpNumber.length == 6) {
        buttonEnabled = true
    }

//    showOTPError = true
//    otpErrorText = "Invalid OTP"

    if (isSignIn) {
        buttonText = "Verify"
        headingText = "Welcome back!"
    } else {
        buttonText = "Verify"
        headingText = "Enter\n" + "Verification Code"
    }

    if (state?.isLoading == true) {
        showLoader = true
    }

    if (state?.error?.isNotBlank() == true) {
        buttonClicked = false
        showLoader = false
        Toast.makeText(context, state?.error, Toast.LENGTH_SHORT).show()
        Logger.d("API_ERROR_RESPONSE", state!!.error)
    }

    state?.data?.let {
        LaunchedEffect(Unit) {
            showLoader = false
            navigateToScreen = true
        }
    }

    if (navigateToScreen && buttonClicked) {
        LaunchedEffect(Unit) {
            showLoader = false
            buttonClicked = false
            navigateToScreen = false
            coroutineScope.launch {
                if (isSignIn) {
                    navController.navigate("main_feature") {
                        popUpTo("onboarding") {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .clickable(
            interactionSource = emptyInteractionSource,
            indication = null,
            onClick = {
                focusManager.clearFocus()
            }
        )
    ) {
        // Background Image
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.light_blue_gradient_image),
            contentDescription = null // Provide a description if needed
        )
        // Column content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp, end = 16.dp, bottom = 16.dp, top = statusBarHeight
                ), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = modifier
                    .padding(top = 25.dp)
                    .align(alignment = Alignment.Start)
                    .clickable(onClick = {
                        if (onEvent != null) {
                            onEvent(OnBoardingEvent.RefreshState)
                        }
                        navController.navigateUp()
                    }),
                painter = painterResource(
                    id = R.drawable.ic_left_arrow
                ),
                contentDescription = "",
            )

            Text(
                text = headingText,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 40.dp, bottom = 32.dp, start = 0.dp, end = 0.dp
                    ),
                letterSpacing = 0.01.sp,
                fontSize = 28.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontFamily = FontFamilyClass.gilroyFamily,
                fontWeight = FontWeight.Bold,
//                style = LocalTextStyle.current.copy(lineHeight = 34.sp)
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        //For line spacing we use .em, So it will give spacing between lines
                        lineHeight = 1.3.em,
                    )
                )
            )
            Text(
                text = "We have sent a 6-digit verification code on:",
                modifier = modifier.fillMaxWidth(),
                letterSpacing = 0.01.sp,
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontFamily = FontFamilyClass.gilroyFamily,
                fontWeight = FontWeight.Medium
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 25.dp),
            ) {
                Text(
                    text = globalMobileNumber,
                    letterSpacing = 0.01.sp,
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamilyClass.gilroyFamily,
                    fontWeight = FontWeight.Medium
                )
                Image(
                    modifier = modifier
                        .padding(start = 16.dp)
                        .clickable(onClick = {
                            if (onEvent != null) {
                                onEvent(OnBoardingEvent.RefreshState)
                            }
                            navController.navigateUp()
                        }),
                    painter = painterResource(
                        id = R.drawable.ic_edit_icon
                    ),
                    contentDescription = "",
                )

            }

            Text(
                text = "Enter Code",
                fontFamily = FontFamilyClass.gilroyFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 0.dp),
                textAlign = TextAlign.Start,
                color = Color(0xFF394157)
            )

            //OTP Fields
            Row {
                for (i in 0 until fieldCount) {
                    OutlinedTextField(
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done,
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            if (otpNumber.length == 6) {
                                buttonClicked = true
                                focusManager.clearFocus()
                            } else {
                                otpErrorText = "Please enter 6 digit otp"
                                showOTPError = true
                            }
                        }),
                        singleLine = true,
                        colors = customColors,
                        onValueChange = { newValue ->
                            showOTPError = false
                            otpErrorText = ""
                            if (!newValue.isDigitsOnly()) {
                                return@OutlinedTextField
                            }
                            if (newValue.length <= 1) {
                                textFieldsText[i] = newValue
                                if (newValue.isNotEmpty()) {
                                    if (i < fieldCount - 1) {
                                        try {
                                            focusRequesters[i + 1].requestFocus()
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                    }
                                }
                            } else {
                                if (i < fieldCount - 1) {
                                    val modifiedString =
                                        newValue.replaceFirst(textFieldsText[i], "")
                                    textFieldsText[i] = modifiedString
                                    focusRequesters[i + 1].requestFocus()
                                } else {
                                    val modifiedString =
                                        newValue.replaceFirst(textFieldsText[i], "")
                                    textFieldsText[i] = modifiedString
                                }
                            }
                        },
                        value = textFieldsText[i],
                        isError = showOTPError,
                        modifier = Modifier
                            .focusRequester(focusRequesters[i])
                            .onKeyEvent { keyEvent ->
                                if (keyEvent.key == Key.Backspace && textFieldsText[i].isEmpty() && i > 0) {
                                    textFieldsText[i] = ""
                                    focusRequesters[i - 1].requestFocus()
                                    true
                                } else {
                                    false
                                }
                            }
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) {
                                    textFieldsText[i] = textFieldsText[i]
                                }
                            }
                            .weight(1f)
                            .padding(4.dp),
                        textStyle = customTextStyle,
                    )
                }
            }
            Text(
                text = otpErrorText,
                fontFamily = FontFamilyClass.gilroyFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp, 0.dp, 8.dp),
                textAlign = TextAlign.Start,
                color = Color.Red
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                CustomButton(
                    onClick = {
                        // Handle button click action
//                        otpErrorText = textFieldsText.joinToString(separator = "") { it }
                        buttonClicked = true
                        focusManager.clearFocus()

                    }, text = buttonText, enabled = buttonEnabled, showLoader = showLoader
                )
                Spacer(modifier = Modifier.height(24.dp))
                MyClickableSpannableText("Don’t have an account? ", "Sign-Up", onClick = {
                    //Click Handler
                    //Type Something
                    coroutineScope.launch {
                        navController.navigate(Screen.SignUpPage.route)
                    }
                })
                Spacer(modifier = Modifier.height(42.dp))
            }

        }
    }

    if (buttonClicked) {
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                if (onEvent != null) {
                    onEvent(
                        OnBoardingEvent.VerifyOTP(
                            mobileNumber = "89774741099",
                            otpNumber = otpNumber
                        )
                    )
                }
            }
        }
//        buttonClicked = false
    }
}

// Extension function to check if a string consists only of digits
fun String.isDigitsOnly(): Boolean {
    return all { it.isDigit() }
}

@Preview(showBackground = true, group = "Signin Page")
@Composable
fun VerifyOTPPreview() {
    AceOnlineTheme {
        VerifyOTPScreen(
            navController = rememberNavController(),
            stateFlow = MutableStateFlow(OnBoardingStateHolder()),
            onEvent = {}
        )
    }
}