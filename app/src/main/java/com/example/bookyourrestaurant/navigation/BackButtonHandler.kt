package com.example.bookyourrestaurant.navigation

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalLifecycleOwner

// Custom CompositionLocal for OnBackPressedDispatcher
private val LocalOnBackPressedDispatcher = staticCompositionLocalOf<OnBackPressedDispatcherOwner?> { null }

// Custom OnBackPressedCallback handler
private class ComposableBackNavigationHandler(enable: Boolean) : OnBackPressedCallback(enable) {
   lateinit var onBackPressed: () -> Unit

   override fun handleOnBackPressed() {
      onBackPressed()
   }
}

@Composable
internal fun ComposableHandler(
   enable: Boolean = true,
   onBackPressed: () -> Unit
) {
   // Retrieve the dispatcher from the current CompositionLocal
   val dispatcher = (LocalOnBackPressedDispatcher.current ?: return).onBackPressedDispatcher
   val handler = remember { ComposableBackNavigationHandler(enable) }

   // Add and remove the handler from the dispatcher
   DisposableEffect(dispatcher) {
      dispatcher.addCallback(handler)
      onDispose { handler.remove() }
   }

   // Update the handler properties whenever they change
   LaunchedEffect(enable) {
      handler.isEnabled = enable
      handler.onBackPressed = onBackPressed
   }
}

@Composable
internal fun SystemBackButtonHandler(onBackPressed: () -> Unit) {
   val lifecycleOwner = LocalLifecycleOwner.current

   // Check if the lifecycleOwner is a ComponentActivity
   val dispatcherOwner = lifecycleOwner as? ComponentActivity

   CompositionLocalProvider(
      LocalOnBackPressedDispatcher provides dispatcherOwner
   ) {
      ComposableHandler(onBackPressed = onBackPressed)
   }
}



