//package com.frost.leap.architecture.onboarding.presentation.screens
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.performTextInput
//import androidx.navigation.compose.rememberNavController
//import com.frost.leap.architecture.onboarding.presentation.OnBoardingStateHolder
//import com.frost.leap.ui.theme.AceOnlineTheme
//import kotlinx.coroutines.flow.MutableStateFlow
//import org.junit.Rule
//import org.junit.Test
//
///**
// * Created by Chenna Rao on 30/06/2023.
// * Frost Interactive
// */
//class SignInScreenTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Composable
//    fun TestSignInPage() {
//        AceOnlineTheme {
//            SignInPage(
//                navController = rememberNavController(),
//                stateFlow = MutableStateFlow(OnBoardingStateHolder()),
//                onEvent = {},
//            )
//        }
//    }
//
//    @Test
//    fun testInvalidMobileNumber() {
//        composeTestRule.setContent {
//            TestSignInPage()
//        }
//
//        composeTestRule.onNodeWithText("Mobile Number")
//            .performTextInput("12345")
//
//        composeTestRule.onNodeWithText("Get OTP")
//            .performClick()
//
//        composeTestRule.onNodeWithText("Please enter valid mobile number")
//            .assertIsDisplayed()
//    }
//
//    @Test
//    fun testValidMobileNumber() {
//        composeTestRule.setContent {
//            TestSignInPage()
//        }
//
//        composeTestRule.onNodeWithText("Mobile Number")
//            .performTextInput("123456789012345")
//
//        composeTestRule.onNodeWithText("Get OTP")
//            .performClick()
//
//        composeTestRule.onNodeWithText("Please enter valid mobile number")
//            .assertDoesNotExist()
//    }
//}
//
//
///*
//*In this test class:
//TestSignInPage is a composable function that sets up the SignInPage within the app’s theme.
//testInvalidMobileNumber checks that an error message is displayed when an invalid mobile number is entered.
//testValidMobileNumber verifies that the error message is not displayed when a valid mobile number is entered.
//Make sure to update the composable functions and string resources accordingly to match the text queries in the test cases.
//If necessary, replace "Mobile Number" and "Please enter valid mobile number" with the actual strings used in your app.
// */