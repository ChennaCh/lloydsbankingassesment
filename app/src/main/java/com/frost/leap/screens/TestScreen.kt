package com.frost.leap.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.frost.leap.R
import com.frost.leap.ui.theme.AceOnlineTheme
import com.frost.leap.utils.FontFamilyClass

data class Sample(val name: String)

val data = listOf(
    Sample("Item1"),
    Sample("Item2"),
    Sample("Item3"),
    Sample("Item4"),
    Sample("Item5"),
    Sample("Item6"),
    Sample("Item7"),
    Sample("Item8"),
    Sample("Item9"),
    Sample("Item10"),
    Sample("Item11"),
    Sample("Item12"),
    Sample("Item13"),
    Sample("Item14"),
    Sample("Item15"),
    Sample("Item16"),
    Sample("Item17")
)

val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
val EXPANDED_TOP_BAR_HEIGHT = 200.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TestScreenCompose(navController: NavController, modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    val isCollapsed: Boolean by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    Scaffold(
        topBar = {
            CollapsedTopBar(isCollapsed = isCollapsed)
        }) { padding ->
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                item {
                    SearchBoxText(
                        testTag = "test",
                        "",
                        onValueChange = {},
                        label = listOf(
                            labelPack("Search for", FontWeight.Normal),
                            labelPack(" courses", FontWeight.SemiBold)
                        ),
                        keyboardType = KeyboardType.Text,
                        ImeAction.Next, // Set the desired input type here
                        showError = false,
                        mobileErrorText = "",
                        onImeActionPerformed = {
                        }
                    )
                }
                items(data) { item ->
                    Item(item)
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun ExpandedTopBar() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .height(
                EXPANDED_TOP_BAR_HEIGHT - COLLAPSED_TOP_BAR_HEIGHT
            ),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Scroll Test",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
private fun CollapsedTopBar(modifier: Modifier = Modifier, isCollapsed: Boolean) {
    val color: Color by animateColorAsState(
        if (isCollapsed) {
            MaterialTheme.colorScheme.background
        } else {
            MaterialTheme.colorScheme.primary
        }
    )
    Box(
        modifier = modifier
            .background(color)
            .fillMaxWidth()
            .height(COLLAPSED_TOP_BAR_HEIGHT)
            .padding(16.dp), contentAlignment = Alignment.BottomStart
    ) {
        AnimatedVisibility(visible = isCollapsed) {
            Text(text = "Scroll Test", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
fun Item(
    sample: Sample, modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier.padding(16.dp)) {
            Text(sample.name)
            Text("This is: " + sample.name)
        }
    }
}


data class labelPack(val label: String, val weight: FontWeight)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBoxText(
    testTag: String,
    value: String,
    onValueChange: (String) -> Unit,
    label: List<labelPack>,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    showError: Boolean,
    mobileErrorText: String,
    onImeActionPerformed: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val customShape = RoundedCornerShape(8.dp) // Increase the corner radius
    var isFocused by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(0) }
    textFieldValue = value.length

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
        fontSize = 16.sp, // Change the font size
        fontWeight = FontWeight.Medium, // Change the font weight
        fontFamily = FontFamilyClass.gilroyFamily,
        letterSpacing = 0.1.sp
    )

    Column {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.White)
                .clip(customShape)
                .border(
                    width = 1.dp,
                    color = if (isFocused) Color(0XFF1D4ED8) else Color(0XFFD1D6E3),
                    shape = customShape
                )
                .clickable { /* Handle click on the text field if needed */ }
                .padding(horizontal = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "Search Image",
                    contentScale = ContentScale.Crop,
                )
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    isError = showError,
                    label = {
                        if (isFocused || textFieldValue > 1) {
                            Text(text = "")
                        } else {
                            Row(
                                verticalAlignment = Alignment.CenterVertically, // Adjust the vertical alignment
                                modifier = Modifier.weight(1f)
                            ) {
                                for (labels in label) {
                                    Text(
                                        text = labels.label,
                                        color = Color(0XFF6F778C),
                                        fontFamily = FontFamilyClass.gilroyFamily,
                                        fontWeight = labels.weight,
                                        letterSpacing = 0.01.sp,
                                        fontSize = 16.sp,
                                        maxLines = 1,
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag(testTag)
                        .onFocusChanged {
                            isFocused = it.isFocused
                        },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        autoCorrect = false,
                        imeAction = imeAction,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    keyboardActions = KeyboardActions(onDone = { onImeActionPerformed() }),
                    textStyle = customTextStyle,
                    colors = customColors,
                    shape = customShape,
                )
            }
        }
        if (mobileErrorText.isNotEmpty()) {
            Text(
                text = mobileErrorText,
                modifier = Modifier.padding(start = 0.dp, top = 2.dp),
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                fontFamily = FontFamilyClass.gilroyFamily,
                fontWeight = FontWeight.Normal,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun TestScreenPreview() {
    AceOnlineTheme {
        TestScreenCompose(navController = rememberNavController())
    }
}

@Preview
@Composable
fun ExpandedTopBarPreview() {
    ExpandedTopBar()
}

@Preview
@Composable
fun CollapsedTopBarPreview() {
    CollapsedTopBar(isCollapsed = true)
}

@Preview(showBackground = true)
@Composable
fun SearchBoxTextPreview() {
    AceOnlineTheme {
        Column {
            Spacer(modifier = Modifier.height(100.dp))
            SearchBoxText(
                testTag = "test",
                "",
                onValueChange = {},
                label = listOf(
                    labelPack("Search for", FontWeight.Normal),
                    labelPack(" courses", FontWeight.SemiBold)
                ),
                keyboardType = KeyboardType.Text,
                ImeAction.Next, // Set the desired input type here
                showError = false,
                mobileErrorText = "",
                onImeActionPerformed = {
                }
            )
        }
    }
}
