@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.bookyourrestaurant.componants

import android.text.Layout
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookyourrestaurant.R
import com.example.bookyourrestaurant.componentShape
import com.example.bookyourrestaurant.data.NavigationItem
import com.example.bookyourrestaurant.ui.theme.Primary
import com.example.bookyourrestaurant.ui.theme.Secondary
import com.example.bookyourrestaurant.ui.theme.TextColor

@Composable
fun NormalTextComponent(value: String){

    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,


        )
         , color = colorResource(id = R.color.colorText),
         textAlign = TextAlign.Center

        )

}
@Composable
fun HeadingTextComponent(value: String){
    Text(text = value,
        modifier = Modifier
            .fillMaxSize().padding(20.dp),

        style = TextStyle(
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        )
        , color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center

    )

}

@Composable
fun MyTextFieldComponent(labelValue: String, painterResource: Painter,
                 onTextSelected: (String) -> Unit,
                         errorStatus : Boolean = false
) {
         val textValue = remember {
             mutableStateOf("")
         }
  //  val  localFocusManager = LocalFocusManager.current
      OutlinedTextField(
          modifier = Modifier
              .fillMaxWidth()
              .clip(componentShape.small),
          label = {Text(text = labelValue)},
          value = textValue.value,
          colors = TextFieldDefaults.colors(
              focusedTextColor = Color.Black,
              focusedLabelColor = Color.Black,
              focusedContainerColor = Primary,
              cursorColor = Color.Black
          ),
          keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
          singleLine = true,
          maxLines = 1,
          onValueChange = {
              textValue.value = it
              onTextSelected(it)

          },

          leadingIcon = {
              Icon(painter = painterResource, contentDescription = "")

          },
          isError = !errorStatus

      )

    }

@Composable
fun PasswordTextField(labelValue: String, painterResource: Painter,
                      onTextSelected: (String) -> Unit,
                      errorStatus : Boolean = false) {

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
      mutableStateOf(false)
    }

    val  localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
            label = {Text(text = labelValue)},
            colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedLabelColor = Color.Black,
            focusedContainerColor = Color.White,
            cursorColor =Color.Black
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,

        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()

        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource,
                contentDescription = "")
        },
        trailingIcon = {
            val iconImage =
             if(passwordVisible.value){
             Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }
            val description = if(passwordVisible.value){
            stringResource(id = R.string.hide_password)
            }else{
               stringResource(id = R.string.show_password)
            }
            IconButton( onClick = {passwordVisible.value = !passwordVisible.value }){
                Icon(imageVector = iconImage, contentDescription = description )

            }
        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus

    )

}


@Composable
fun TestBoxComponent(value: String, onTextSelected: (String) -> Unit, onCheckChange : (Boolean)-> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        ) {
         val  checkedState = remember {
             mutableStateOf(false)
         }
        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value != checkedState.value
                onCheckChange.invoke(it)

        })

        ClickableTextComponent(value = value, onTextSelected)




    }

}


@Composable
fun ClickableTextComponent(value: String, onTextSelected : (String) -> Unit) {
    val initialText = "By continuing you accept our "
    val privacyPolicy = " Privacy Policy "
    val andText = " and "
    val termAndConditionsText = "Term of Use"
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = privacyPolicy, annotation = privacyPolicy)
            append(privacyPolicy)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = termAndConditionsText, annotation = termAndConditionsText)
            append(termAndConditionsText)
        }
    }
    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span->
                Log.d("ClickableTextComponent", "{${span.item}}")
               if((span.item == termAndConditionsText) || (span.item == privacyPolicy)) {
                   onTextSelected(span.item)
               }

            }
    })
}

@Composable
fun ButtonComponent( value: String, onButtonClicked : () -> Unit , isEnabled: Boolean = false){
   Button(
       modifier = Modifier
           .fillMaxWidth()
           .heightIn(48.dp),
       onClick = {
           onButtonClicked.invoke()
       },
       contentPadding = PaddingValues(),
       colors = ButtonDefaults.buttonColors(Color.Transparent),
       shape = RoundedCornerShape(50.dp),
       enabled = isEnabled
   ) {
       Box(modifier = Modifier.fillMaxWidth()
           .heightIn(48.dp).background(
               brush = Brush.horizontalGradient(listOf(Secondary , Primary )),
               shape = RoundedCornerShape(50.dp),

           ), contentAlignment = Alignment.Center
       ){
           Text(text = value,
               fontSize = 18.sp,
               fontWeight = FontWeight.Bold

               )

       }

   }
}
@Composable
fun DividerTextComponent(){
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        HorizontalDivider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.DarkGray,
            thickness = 2.dp)
        Text(modifier = Modifier.padding(4.dp), text = stringResource(R.string.or),
            fontSize = 18.sp,
            color = TextColor,
            fontWeight = FontWeight.Bold)

        HorizontalDivider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.DarkGray,
            thickness = 2.dp)
    }

}

@Composable
fun ClickableLoginTextComponent(tryingToLogin:Boolean = true, onTextSelected : (String) -> Unit) {
    val initialText = if(tryingToLogin)"Already Have An Account?" else "Don't Have an Account? "
    val loginText= if(tryingToLogin)"Login" else "Register"
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Color.Blue)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }

        }

    ClickableText(

        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center

            ),


        text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span->
                Log.d("ClickableTextComponent", "{${span.item}}")
                if((span.item == loginText)) {
                    onTextSelected(span.item)
                }

            }
    })
}
@Composable
fun UnderlineTextComponent(value: String){

    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Serif,

            )
        , color = colorResource(id = R.color.colorGray),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline

    )



}

@Composable fun AppToolBar(toolbarTitle: String, logoutButtonClicked: () -> Unit, navigationIconClicked: () -> Unit)
{ TopAppBar( title = { Text(toolbarTitle) },
    navigationIcon = { IconButton(onClick = navigationIconClicked)
    { Icon( painter = painterResource(id = R.drawable.baseline_menu_24),
        contentDescription = stringResource(id = R.string.menu) ) } },
    actions = { IconButton(onClick = logoutButtonClicked)
    { Icon( painter = painterResource(id = R.drawable.baseline_logout_24),
        contentDescription = stringResource(id = R.string.logout) ) } } ) }


@Composable
fun NavigationDrawerBody(navigationDrawerItems: List<NavigationItem>)
{ Column( modifier = Modifier .fillMaxHeight() .width(300.dp)
    .background(Color.Cyan) )
{ NavigationDrawerHeader()
    Divider(color = Color.Gray)
    Spacer(modifier = Modifier.height(20.dp))
    LazyColumn( modifier = Modifier
        .fillMaxSize()
        .weight(5f) )
    { items(navigationDrawerItems) { item -> NavigationItemRow(item = item) } } } }

@Composable
fun NavigationDrawerHeader() {
    Column (
        modifier = Modifier.fillMaxWidth()

            .background(Color.Green),
             horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(25.dp))
        Image(
            painter = painterResource(R.drawable.restaurant),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
                .padding(all = 30.dp)

        )

        Spacer(modifier = Modifier.height(16.dp))
        Text( text = "Welcome Back ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
                .height(20.dp)
                .align(Alignment.CenterHorizontally)
                .size(20.dp))
    }

    Box(

        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp)
    ) {

            Spacer(modifier = Modifier.height(16.dp))
            HeadingTextComponent(
                value = stringResource(R.string.welcome))
        }
    }

@Composable
fun NavigationItemRow(item : NavigationItem){
    Spacer(modifier = Modifier.height(10.dp))
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(all = 8.dp))
    {
        Icon(
            imageVector = item.icon,
            contentDescription = item.description)
        Spacer(modifier = Modifier.width(10.dp))
        NormalTextComponent(value = item.title)

    }
}
