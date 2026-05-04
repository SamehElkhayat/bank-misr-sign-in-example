package com.example.bankmisr

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.bankmisr.ui.theme.BankMisrTheme
import com.example.bankmisr.ui.theme.RedDark

lateinit var usernameState: TextFieldState
lateinit var passwordState: TextFieldState
var enLanguage by mutableStateOf(true)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankMisrTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BankMisrIntro(
                        modifier = Modifier.padding(innerPadding), enLanguage
                    )
                }
            }
        }
    }
}

@Composable
private fun BankMisrIntro(modifier: Modifier = Modifier, enLanguage: Boolean = true) {
    if (enLanguage) BankMisrSignin() else BankMisrSigninAr()
}

@Composable
fun BankMisrSigninAr(modifier: Modifier = Modifier) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .padding(all = 16.dp)
                .padding(top = 24.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.bm_icon),
                    contentDescription = "شعار بنك مصر"
                )
                TextButton(
                    onClick = {
                        enLanguage = true

                    }
                ) {
                    Text(
                        text = "En",
                        color = RedDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }

            }
            UsernameTextField("اسم المستخدم")
            PasswordTextField("كلمة السر")

            Text(
                text = buildAnnotatedString {
                    withLink(
                        LinkAnnotation.Url(
                            url = "https://www.linkedin.com/in/sameh-elkhayat",
                            styles = TextLinkStyles(
                                SpanStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    textDecoration = TextDecoration.Underline
                                )
                            )
                        )
                    ) {
                        append("نسيت أسم الاسمتخدم/كلمة المرور؟")
                    }
                },
                modifier = Modifier.padding(vertical = 8.dp)

            )

            LoginButton("تسجيل الدخول")
            Text(
                text = buildAnnotatedString {
                    append("مزيد من المساعدة?")
                    withLink(
                        LinkAnnotation.Url(
                            url = "https://www.linkedin.com/in/sameh-elkhayat",
                            styles = TextLinkStyles(
                                SpanStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    textDecoration = TextDecoration.Underline
                                )
                            )
                        )
                    ) {
                        append("اتصل بنا")
                    }
                },
                modifier = Modifier.padding(vertical = 8.dp)

            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 64.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                NavigationButtons("منتجاتنا", R.drawable.our_products)
                NavigationButtons("سعر الصرف", R.drawable.exchange_rate)
                NavigationButtons("نصائح أمنية", R.drawable.security_tips)
                NavigationButtons("أقرب فرع أو ماكينة صراف آلي", R.drawable.nearest_branch_or_atm)
            }

        }
    }

}


@Composable
fun BankMisrSignin(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(all = 16.dp)
            .padding(top = 24.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.bm_icon),
                contentDescription = "Bank Misr Logo"
            )
            TextButton(
                onClick = {
                    enLanguage = false

                }
            ) {
                Text(
                    text = "العربية",
                    color = RedDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

        }
        UsernameTextField()
        PasswordTextField()

        Text(
            text = buildAnnotatedString {
                withLink(
                    LinkAnnotation.Url(
                        url = "https://www.linkedin.com/in/sameh-elkhayat",
                        styles = TextLinkStyles(
                            SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    )
                ) {
                    append("Forgot username/password?")
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)

        )

        LoginButton()
        Text(
            text = buildAnnotatedString {
                append("Need Help?")
                withLink(
                    LinkAnnotation.Url(
                        url = "https://www.linkedin.com/in/sameh-elkhayat",
                        styles = TextLinkStyles(
                            SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    )
                ) {
                    append("Contact Us")
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)

        )
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 64.dp),
            thickness = 1.dp,
            color = Color.Gray
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            NavigationButtons("Our Products", R.drawable.our_products)
            NavigationButtons("Exchange Rate", R.drawable.exchange_rate)
            NavigationButtons("Security Tips", R.drawable.security_tips)
            NavigationButtons("Nearest Branch or ATM", R.drawable.nearest_branch_or_atm)
        }

    }

}

@Composable
private fun NavigationButtons(serviceName: String, serviceImage: Int) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(serviceImage),
            contentDescription = "Our Products",
            modifier = Modifier
                .padding(top = 32.dp)
                .size(64.dp, 64.dp)
        )
        Text(
            text = serviceName,
            modifier = Modifier
                .padding(top = 8.dp),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,


            )
    }
}

@Composable
private fun LoginButton(buttonName: String = "Login") {
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                "https://play.google.com/store/apps/details?id=com.elkhayat.momenpro".toUri()
            )
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(
            RedDark
        ),
        enabled = usernameState.text != "" && passwordState.text != "",
        shape = RoundedCornerShape(10.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(buttonName)
    }
}

@Composable
private fun UsernameTextField(userNameLabel: String = "User Name") {
    usernameState = rememberTextFieldState()
    OutlinedTextField(
        state = usernameState,
        label = {
            Text(
                text = userNameLabel,
                fontSize = 18.sp
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    )
}


@Composable
private fun PasswordTextField(passwordLabel: String = "Password") {
    passwordState = rememberTextFieldState()
    var showPassword by remember { mutableStateOf(false) }

    OutlinedSecureTextField(
        state = passwordState,
        label = {
            Text(
                text = passwordLabel
            )
        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        textObfuscationMode = if (showPassword) {
            TextObfuscationMode.Visible
        } else {
            TextObfuscationMode.Hidden
        },
        trailingIcon = {
            val image = if (showPassword)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Please provide localized description for accessibility services
            val description = if (showPassword) "Hide password" else "Show password"

            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(imageVector = image, description)
            }
        }
    )
}

@Preview(device = "spec:width=411dp,height=891dp", showSystemUi = true)
@Composable
fun BankMisrSigninPreview() {
    BankMisrTheme {
        BankMisrSignin()
    }
}