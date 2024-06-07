package com.frost.leap.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.core.view.WindowCompat
import com.frost.leap.routing.Navigation
import com.frost.leap.storage.DataStoreManager
import com.frost.leap.utils.Constants
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var dataStoreManager: DataStoreManager

    companion object {
        lateinit var currentDestinationSplashActivity: String
        var isSignIn: Boolean = false
    }

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        // expanded the app UI to system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState)


//        setContent {
//            AceOnlineTheme {
//                // Will Show the splash screen initially
//                SplashScreen(navController = rememberAnimatedNavController())
//            }
//        }

        setContent {
            MaterialTheme {
                val navController = rememberAnimatedNavController()
                Navigation(navController)
            }

            // Handle back call
            onBackPressedDispatcher.addCallback(this@MainActivity, onBackPressedCallback)
        }

        // Additional initialization or setup code for your app
//        lifecycleScope.launch {
//            // Simulating a delay of 2 seconds
//            delay(2000)
//            /*
//            *Dispatchers.Main ->Main Thread
//            * Dispatchers.Default -> CPU Intensive task
//            *  * Dispatchers.IO -> Separate Thread
//            * */
//            withContext(Dispatchers.IO) {
//                // After the delay, replace the SplashScreen with your actual app content
//                setContent {
//                    MaterialTheme {
//                        val navController = rememberAnimatedNavController()
//                        com.frost.leap.routing.Navigation(navController)
//                    }
//
////                    // Handle back call
////                    onBackPressedDispatcher.addCallback(this@MainActivity, onBackPressedCallback)
//                }
//            }
//        }
    }

    // define back cases
    private val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                when (currentDestinationSplashActivity) {
                    Constants.NavigationStrings.SIGN_UP_NAVIGATION -> {
                        finish()
                    }

                    Constants.NavigationStrings.OTP_NAVIGATION -> {
                        
                    }

                    Constants.NavigationStrings.SIGN_IN_NAVIGATION -> {
                        //CustomCreated inside SignInClass
                        finish()
                    }

                    Constants.NavigationStrings.ONBOARDING_NAVIGATION -> {
                        finish()
                    }

                    Constants.NavigationStrings.VERIFY_OTP_NAVIGATION -> {
                        finish()
                    }

                    Constants.NavigationStrings.VIDEO_PLAYER_SCREEN -> {
                        finish()
                    }
                }
            }
        }
}

//@Composable
//fun RedirectToOnBoardingPage(
//    navController: NavController, modifier: Modifier = Modifier
//) {
//    currentDestinationSplashActivity = Constants.NavigationStrings.ONBOARDING_NAVIGATION
//    navController.navigate(Screen.OnBoardingPage.route)
//    {
//        // Pop up to the main screen, removing the splash screen from the back stack
//        popUpTo(Constants.NavigationStrings.SPLASH_NAVIGATION) {
//            inclusive = true
//        }
//    }
//
//}
