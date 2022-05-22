package com.adriann.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adriann.example.businesscard.ui.theme.BusinessCardTheme

data class Contact(
    val name: String,
    val jobDescription: String,
    val phoneNumber: String,
    val socialMedia: String,
    val email: String
)

val demoContact = Contact(
    name = "Jennifer Doe",
    jobDescription = "Android Developer Extraordinaire",
    phoneNumber = "+11 (123) 444 555 666",
    socialMedia = "@AndroidDev",
    email = "jen.doe@android.com"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BusinessCard(demoContact)
                }
            }
        }
    }
}

@Composable
fun BusinessCard(contact: Contact) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BusinessCardSummary(
            name = contact.name,
            jobDescription = contact.jobDescription,
            modifier = Modifier.fillMaxHeight(0.75F)
        )
        BusinessCardDetails(
            phoneNumber = contact.phoneNumber,
            socialMedia = contact.socialMedia,
            email = contact.email
        )
    }
}

@Composable
fun BusinessCardSummary(name: String, jobDescription: String, modifier: Modifier = Modifier) {
    val logoImage = painterResource(id = R.drawable.android_logo)
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = logoImage, contentDescription = null,
            modifier = Modifier.fillMaxHeight(0.1F)
        )
        Text(
            text = name,
            fontSize = 36.sp,
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Text(text = jobDescription, fontSize = 16.sp, color = MaterialTheme.colors.secondary)
    }
}

@Composable
fun BusinessCardDetails(
    phoneNumber: String,
    socialMedia: String,
    email: String
) {
    Column {
        BusinessCardDetailsCell(
            icon = Icons.Rounded.Phone,
            info = phoneNumber,
            contentDescription = stringResource(R.string.phone_desc)
        )
        BusinessCardDetailsCell(
            icon = Icons.Rounded.Share,
            info = socialMedia,
            contentDescription = stringResource(R.string.social_media_desc)
        )
        BusinessCardDetailsCell(
            icon = Icons.Rounded.Email,
            info = email,
            contentDescription = stringResource(R.string.email_desc)
        )
    }
}

@Composable
fun BusinessCardDetailsCell(
    icon: ImageVector,
    info: String,
    contentDescription: String,
) {
    Divider(thickness = 2.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(start = 32.dp, end = 16.dp)
        )
        Text(
            text = info, color = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF003A4A)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        BusinessCard(demoContact)
    }
}