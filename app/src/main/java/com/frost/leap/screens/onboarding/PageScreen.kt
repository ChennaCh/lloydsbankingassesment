package com.frost.leap.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.frost.leap.model.BenefitModel
import com.frost.leap.utils.Utility

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageScreen(
    navController: NavController,
    pageCount: Int,
    pagerState: PagerState,
    onBoardingPageData: BenefitModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
        ) {
            val contentScale = if (pagerState.currentPage == 3) {
                ContentScale.Crop
            } else {
                ContentScale.Fit
            }

            Image(
                painter = painterResource(id = onBoardingPageData.image),
                contentDescription = "banner image",
                modifier = Modifier.fillMaxSize(),
                contentScale = contentScale,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PageScreenPreview() {
    PageScreen(
        rememberNavController(), 4, rememberPagerState(), Utility.getBenefitsList()!![0]
    )
}