package com.frost.leap.architecture.onboarding.presentation.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import com.frost.leap.architecture.onboarding.presentation.screens.SignupData.globalMobileNumber
import com.frost.leap.components.CustomButton
import com.frost.leap.components.CustomTextField
import com.frost.leap.components.MyClickableSpannableText
import com.frost.leap.routing.Screen
import com.frost.leap.ui.theme.AceOnlineTheme
import com.frost.leap.utils.Constants
import com.frost.leap.utils.FontFamilyClass
import com.frost.leap.utils.Utility
import com.support.components.CustomAlert
import kotlinx.coroutines.launch

object SignupData {
    var globalMobileNumber: String = "I"
}

@Composable
fun SignUpPage(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    currentDestinationSplashActivity = Constants.NavigationStrings.SIGN_UP_NAVIGATION
    isSignIn = false

    var fullName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("+91  ") }
    var buttonEnabled by remember { mutableStateOf(false) }
    var showLoader by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var mobileErrorText by remember { mutableStateOf("") }
    var showMobileError by remember { mutableStateOf(false) }
    val mobileNumberString = globalMobileNumber
    val statusBarHeight = Utility.getStatusBarHeight(context = context)
    val emptyInteractionSource = remember { MutableInteractionSource() }

    //You can use the rememberCoroutineScope to launch the navigation process safely
    val coroutineScope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current

    buttonEnabled = fullName.length > 1 && mobileNumber.length > 5

    //OnBackPressed
//    BackHandler(onBackPressed = {
//        navController.navigateUp()
//    })

    Box(
        modifier = Modifier
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
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.light_blue_gradient_image),
            contentDescription = null // Provide a description if needed
        )
        // Column content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(start = 16.dp, top = statusBarHeight, end = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Let’s get you\n" +
                        "started right away!",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 75.dp,
                        bottom = 32.dp,
                        start = 0.dp,
                        end = 0.dp
                    ),
                letterSpacing = 0.01.sp,
                fontSize = 28.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontFamily = FontFamilyClass.gilroyFamily, fontWeight = FontWeight.Bold,
//                style = LocalTextStyle.current.copy(lineHeight = 34.sp)
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        //For line spacing we use .em, So it will give spacing between lines
                        lineHeight = 1.3.em,
                    )
                )
            )

            Text(
                text = "Full Name",
                fontFamily = FontFamilyClass.gilroyFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color(0xFF394157)
            )
            CustomTextField(
                testTag = "Full_Name_Tag",
                value = fullName,
                onValueChange = { fullName = it.trim() },
                label = "Enter full name",
                keyboardType = KeyboardType.Text, // Set the desired input type here
                imeAction = ImeAction.Next,
                showError = false,
                mobileErrorText = "",
                onImeActionPerformed = {

                },
                containerColor = Color(0xFFF5F5F5)
            )
            Text(
                text = "Mobile number",
                fontFamily = FontFamilyClass.gilroyFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 24.dp, 0.dp, 0.dp),
                textAlign = TextAlign.Start,
                color = Color(0xFF394157)
            )
            CustomTextField(
                testTag = "Mobile_Number_Tag",
                value = mobileNumber,
                onValueChange = { newText ->
                    if (newText.length <= 15) {
                        mobileNumber = newText.trim()
                        globalMobileNumber = newText.trim()
                        showMobileError = false
                        mobileErrorText = ""
                    }
                    if (newText.length <= 5) {
                        mobileNumber = "+91  "
                        globalMobileNumber = "+91  "
                        showMobileError = false
                        mobileErrorText = ""
                    }
                },
                label = "Enter mobile number",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done, // Set the desired input type here,
                showError = showMobileError,
                mobileErrorText = mobileErrorText,
                onImeActionPerformed = {

                },
                containerColor = Color(0xFFF5F5F5)
            )
            Text(
                text = "We’ll send a verification code on this number.",
                fontFamily = FontFamilyClass.gilroyFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp, 0.dp, 8.dp),
                textAlign = TextAlign.Start,
                color = Color(0xFF6F778C)
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                CustomButton(
                    onClick = {
                        // Handle button click action
                        if (mobileNumberString.length < 15) {
                            showMobileError = true
                            mobileErrorText = "Please enter valid mobile number"

                            CustomAlert(
                                context = context,
                                title = "Login Alert",
                                message = "You are not yet registered." +
                                        "\nPlease sign up and sign in again",
                                posCaption = "Sign Up",
                                negCaption = "",
                                setCancelable = true,
                                listener = object :
                                    CustomAlert.DeleteAlertListener() {
                                    override fun onYes() {

                                    }
                                })
                        } else {
                            coroutineScope.launch {
                                navController.navigate(Screen.VideoPlayerPage.route)
                            }

                        }
                    },
                    text = "Next",
                    enabled = buttonEnabled,
                    showLoader = showLoader
                )
                Spacer(modifier = Modifier.height(24.dp))
                MyClickableSpannableText("Already have an account?",
                    "Login",
                    onClick = {
                        //Click Handler
                        //Type Something
                        coroutineScope.launch {
                            navController.navigate(Screen.SignInPage.route)
                        }
                    })
                Spacer(modifier = Modifier.height(42.dp))
            }

        }
    }
}

//@Preview(showBackground = true, group = "Spannable Text")
//@Composable
//fun PreviewSpannableText() {
//    MyClickableSpannableText("", "", onClick = {
//
//    })
//}

@Preview(showBackground = true, group = "Signup Page")
@Composable
fun SignUpPagePreview() {
    AceOnlineTheme {
        SignUpPage(
            navController = rememberNavController()
        )
    }
}

//@Preview(showBackground = true, group = "Login Page")
//@Composable
//fun LoginPagePreview() {
//    AceOnlineTheme {
////        LoginPage("Android")
//    }
//}