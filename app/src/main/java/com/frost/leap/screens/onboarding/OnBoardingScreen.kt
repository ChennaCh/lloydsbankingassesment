package com.frost.leap.screens.onboarding

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.frost.leap.activities.MainActivity.Companion.currentDestinationSplashActivity
import com.frost.leap.architecture.onboarding.presentation.OnBoardingEvent
import com.frost.leap.architecture.onboarding.presentation.OnBoardingStateHolder
import com.frost.leap.components.CustomButton
import com.frost.leap.components.CustomTextField
import com.frost.leap.components.TermsPrivacySpannableText
import com.frost.leap.routing.Screen
import com.frost.leap.screens.onboarding.OnBoardingData.globalMobileNumber
import com.frost.leap.ui.theme.AceOnlineTheme
import com.frost.leap.ui.theme.Blue
import com.frost.leap.ui.theme.L_TEXT_COLOR_MEDIUM
import com.frost.leap.ui.theme.Typography
import com.frost.leap.ui.theme.White
import com.frost.leap.utils.Logger
import com.frost.leap.utils.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


object OnBoardingData {
    var globalMobileNumber: String = "I"
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreenCompose(
    navController: NavController,
    stateFlow: StateFlow<OnBoardingStateHolder?>,
    onEvent: ((OnBoardingEvent) -> Unit)?,
    modifier: Modifier = Modifier
) {

    val benefits = Utility.getBenefitsList()
    val context = LocalContext.current
    val pageCount = 4
    val pagerState = rememberPagerState()
    val statusBarHeight = Utility.getStatusBarHeight(context = context)

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route
    currentDestinationSplashActivity = currentDestination.toString()

    var mobileNumber by remember { mutableStateOf("") }
    var buttonEnabled by remember { mutableStateOf(false) }
    var showLoader by remember { mutableStateOf(false) }
    var mobileErrorText by remember { mutableStateOf("") }
    var showMobileError by remember { mutableStateOf(false) }
    val mobileNumberString = globalMobileNumber
    val focusManager = LocalFocusManager.current
    buttonEnabled = mobileNumber.length > 9

    LaunchedEffect(Unit) {
        if (globalMobileNumber.length > 9) {
            mobileNumber = globalMobileNumber
        }
    }

    //You can use the rememberCoroutineScope to launch the navigation process safely
    val coroutineScope = rememberCoroutineScope()
    var buttonClicked by remember { mutableStateOf(false) }
    var navigateToScreen by remember { mutableStateOf(false) }
    var retryAttempt by remember { mutableStateOf(0) }

    val state by stateFlow.collectAsState()

    Surface(
        color = Color(0xFFF7F7F7),
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column {

//            state.let {
//                Logger.d("API_RESPONSE","isLoading: ${it!!.isLoading}")
//                showLoader = it.isLoading
//            }

            if (state?.error?.isNotBlank() == true) {
                buttonClicked = false
                Toast.makeText(context, state?.error, Toast.LENGTH_SHORT).show()
                Logger.d("API_RESPONSE", state!!.error)
            }

            if (buttonClicked) {
                state?.data?.let {
                    LaunchedEffect(Unit) {
                        Logger.d("API_RESPONSE", "Button Clicked")
                        Toast.makeText(context, it.get("message").asString, Toast.LENGTH_SHORT)
                            .show()
                        navigateToScreen = true
                    }
                }
            }

            if (navigateToScreen && buttonClicked) {
                showLoader = false
                buttonClicked = false
                navigateToScreen = false
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        Logger.d("API_RESPONSE", "Navigagte TO Screen")
                        navController.navigate(Screen.OTPPage.route)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2.1f)
            ) {
                HorizontalPager(
                    state = pagerState,
                    pageCount = pageCount,
                    modifier = Modifier,
                    verticalAlignment = Alignment.Top,
                ) { position ->
                    PageScreen(
                        navController = navController,
                        onBoardingPageData = benefits[position],
                        pageCount = pageCount,
                        pagerState = pagerState
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                        ) {
                            repeat(pageCount) { iteration ->
                                val color =
                                    if (pagerState.currentPage == iteration) Blue else Color(
                                        0xFF007AFF
                                    ).copy(
                                        alpha = 0.2f
                                    )
                                val size = if (pagerState.currentPage == iteration) 6 else 6
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .background(color)
                                        .size(size.dp)
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 8.dp),
                        verticalArrangement = Arrangement.Top
                    ) {

                    }
                }
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
                    text = "Login / Signup",
                    style = Typography.titleLarge,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF007AFF),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 0.dp)
                )
                Text(
                    text = "Enter your phone number to login/signup",
                    style = Typography.titleSmall,
                    textAlign = TextAlign.Start,
                    color = L_TEXT_COLOR_MEDIUM,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp)
                )
                CustomTextField(
                    testTag = "Mobile_Number_Tag",
                    value = mobileNumber,
                    onValueChange = { newText ->
                        if (newText.length <= 10) {
                            mobileNumber = newText.trim()
                            globalMobileNumber = newText.trim()
                            showMobileError = false
                            mobileErrorText = ""
                        }
//                        if (newText.length <= 5) {
//                            mobileNumber = ""
//                            SignInData.globalMobileNumber = ""
//                            showMobileError = false
//                            mobileErrorText = ""
//                        }
                    },
                    label = "Phone Number",
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done, // Set the desired input type here,
                    showError = showMobileError,
                    mobileErrorText = mobileErrorText,
                    onImeActionPerformed = {
                        if (mobileNumberString.length < 10) {
                            showMobileError = true
                            mobileErrorText = "Please enter valid mobile number"

                        } else {
                            focusManager.clearFocus()
                            buttonClicked = true
                            mobileNumber = globalMobileNumber
                            retryAttempt++
                        }
                    },
                    containerColor = Color(0xFFF5F5F5)
                )
                Spacer(modifier = Modifier.height(0.dp))
                CustomButton(
                    onClick = {
                        // Handle button click action
                        if (mobileNumberString.length < 10) {
                            showMobileError = true
                            mobileErrorText = "Please enter valid mobile number"
                            showLoader = !showLoader

                        } else {
                            focusManager.clearFocus()
                            buttonClicked = true
                            mobileNumber = globalMobileNumber
                            retryAttempt++

                        }
                    },
                    text = "CONTINUE",
                    enabled = buttonEnabled,
                    showLoader = showLoader
                )

                Column(
                    modifier = modifier
                        .padding(top = 4.dp, start = 8.dp)
                        .fillMaxWidth()
                ) {
                    TermsPrivacySpannableText("By clicking, I accept the",
                        onTermsClick = {

                            Toast.makeText(context, "terms", Toast.LENGTH_SHORT).show()
                        },
                        onPrivacyClick = {

                            Toast.makeText(context, "privacy", Toast.LENGTH_SHORT).show()
                        }
//                        onClick = {
//                            //Click Handler
//                            coroutineScope.launch {
//                                navController.navigate(Screen.SignInPage.route)
//                                {
//                                    // Pop up to the main screen, removing the onBoarding screen from the back stack
//                                    popUpTo(Constants.NavigationStrings.ONBOARDING_NAVIGATION) {
//                                        inclusive = true
//                                    }
//                                }
//                            }
//                        }
                    )
                }
            }
        }
    }

    if (buttonClicked) {
        LaunchedEffect(key1 = retryAttempt) {
            Logger.d("API_RESPONSE", "$retryAttempt")
            withContext(Dispatchers.IO) {
                state?.error?.let { Logger.d("API_RESPONSE", it); }
                if (state?.error?.isEmpty() == true) {
                    if (onEvent != null) {
                        onEvent(OnBoardingEvent.RequestLoginOrSignupAuth(mobileNumber))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, group = "OnBoardingScreen")
@Composable
fun OnBoardingScreenPreview() {
    AceOnlineTheme {
        OnBoardingScreenCompose(
            navController = rememberNavController(),
            stateFlow = MutableStateFlow(OnBoardingStateHolder()),
            onEvent = {},
        )
    }
}
