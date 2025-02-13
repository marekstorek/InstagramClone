package com.example.instagramclone.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.instagramclone.R
import com.example.instagramclone.UserProfilePhoto
import com.example.instagramclone.clickableNoEffect
import com.example.instagramclone.data.Dummy
import com.example.instagramclone.data.Post
import com.example.instagramclone.inverted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyProfileScreen(){
    val pagerState = rememberPagerState(pageCount = {3}, initialPage = 0)
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        stickyHeader {
            MyProfileScreenTopBar()
        }
        item {
            MyProfilePhotoAndStats()
        }
        item {
            Text(Dummy.myProfile.bio, Modifier.padding(15.dp).widthIn(max = 200.dp), maxLines = 5, overflow = TextOverflow.Ellipsis)
        }
        item {
            MyButtonsRow()
        }
        item {
            MyProfileContentIconsHeader(pagerState, coroutineScope)
        }
        item{
            HorizontalDivider()
        }
        item{
            MyProfileContent(pagerState)
        }
        item{
            Text("Complete your profile",Modifier.padding(top=30.dp, start = 15.dp).wrapContentHeight(align = Alignment.Bottom), fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
        item{
            Text("3 of 4 Complete",Modifier.padding(bottom = 0.dp).padding(horizontal = 15.dp).wrapContentHeight(align = Alignment.Top), fontSize = 10.sp)
        }
        item {
            CompleteProfileRow()
        }
    }
}

@Composable
private fun MyProfileContent(pagerState: PagerState) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.wrapContentHeight(Alignment.Top)) {
        val myReels by rememberSaveable {
            mutableStateOf(
                when (it) {
                    0 -> Dummy.myProfile.posts
                    1 -> Dummy.myProfile.reels
                    else -> Dummy.myProfile.taggedPosts
                }
            )
        }
        PostsRow(myReels)
    }
}

@Composable
private fun MyProfileContentIconsHeader(
    pagerState: PagerState,
    coroutineScope: CoroutineScope
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .padding(top = 30.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            painterResource(if (pagerState.currentPage == 0) R.drawable.ic_more_grid_filled_24 else R.drawable.ic_more_grid_24),
            null,
            modifier = Modifier
                .weight(1f)
                .clickableNoEffect {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                })
        Icon(
            painterResource(if (pagerState.currentPage == 1) R.drawable.ic_reels_filled_24 else R.drawable.ic_reels_24),
            null,
            modifier = Modifier
                .weight(1f)
                .clickableNoEffect {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                })
        Icon(
            painterResource(if (pagerState.currentPage == 2) R.drawable.ic_account_box_filled_24 else R.drawable.ic_account_box_24),
            null,
            modifier = Modifier
                .weight(1f)
                .clickableNoEffect {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(2)
                    }
                })
    }
}

@Composable
private fun MyButtonsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Spacer(Modifier)
        MyButton(
            modifier = Modifier.weight(5f)
        ) {
            Text("Edit profile")
        }
        MyButton(
            modifier = Modifier.weight(5f)
        ) {
            Text("Share profile")
        }
        MyButton(
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painterResource(R.drawable.ic_share_profile_24),
                null,
                modifier = Modifier.padding(7.dp)
            )
        }
        Spacer(Modifier)
    }
}

@Composable
private fun MyProfilePhotoAndStats() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Spacer(Modifier)
        UserProfilePhoto(0, photo = Dummy.myProfile.profilePhoto)
        Column {
            Text(Dummy.myProfile.name)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                NumberAndLabel(Dummy.myProfile.posts.size, "posts")
                NumberAndLabel(Dummy.myProfile.followers, "followers")
                NumberAndLabel(Dummy.myProfile.following, "following")
            }
        }
    }
}

@Composable
fun CompleteProfileRow(){
    Row(Modifier.horizontalScroll(rememberScrollState())) {
        Spacer(Modifier.width(10.dp))
        CompleteProfileCard(
            R.drawable.my_profile_add_bio,
            "Add bio",
            "Tell your followers a little bit about yourself.",
            "Add bio",
            true
        )
        CompleteProfileCard(
            R.drawable.my_profile_add_name,
            "Add your name",
            "Add your full name so your friends know it's you.",
            "Edit name",
            false
        )
        CompleteProfileCard(
            R.drawable.my_profile_add_photo,
            "Add profile picture",
            "Choose a profile picture to represent yourself on Instagram.",
            "Change picture",
            false
        )
        CompleteProfileCard(
            R.drawable.my_profile_find_followers,
            "Find people to follow",
            "Follow people and interests you care about.",
            "Find more",
            false
        )

    }
}

@Composable
fun CompleteProfileCard(
    @DrawableRes imageSrc: Int,
    title: String,
    text: String,
    buttonTitle: String,
    clickable: Boolean = false
){

    Card(
        Modifier.padding(5.dp).clip(RoundedCornerShape(10.dp)).height(220.dp).width(200.dp),
        border = BorderStroke(0.5.dp, Color.Black),
    ) {
        Column(
            Modifier.fillMaxSize().background(MaterialTheme.colorScheme.onBackground.inverted()).padding(vertical = 15.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.size(15.dp))
            Image(painterResource(imageSrc),null,Modifier.size(70.dp), contentScale = ContentScale.Fit)
            Spacer(Modifier.size(5.dp))
            Text(title, textAlign = TextAlign.Center,fontWeight = FontWeight.Bold, modifier =  Modifier.padding(horizontal = 12.dp), lineHeight = 20.sp, fontSize = 13.5.sp)
            Text(
                text,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(2f),
                fontSize = 11.sp,
                lineHeight = 15.sp,
            )
            MyButton(modifier = Modifier,clickable = clickable) {
                Text(buttonTitle, Modifier.padding(horizontal = 15.dp), color = it, fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun PostsRow(myReels: List<Post>){
    Column {
        for (i in 0..(((myReels.size-1) / 3))){
            Row(Modifier.fillMaxWidth()) {
                for (j in 0..2) {
                    val index = 3*i+j
                    if (index >= myReels.size){
                        Spacer(Modifier.weight(1f))
                        continue
                    }
                    Image(
                        painter = rememberAsyncImagePainter(myReels[3 * i + j].photo), null,
                        Modifier.weight(1f).aspectRatio(3 / 4f).padding(1.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

    }

}

@Composable
fun MyButton(modifier: Modifier = Modifier, clickable: Boolean = false,content: @Composable (Color) -> Unit){
    val backgroundColor: Color
    val borderColor: Color
    val textColor: Color
    if (clickable){
        backgroundColor = Color.Blue
        borderColor = Color.Blue
        textColor = Color.White
    } else {
        backgroundColor = Color.Transparent
        borderColor = MaterialTheme.colorScheme.background.inverted()
        textColor = MaterialTheme.colorScheme.onBackground
    }
    Box(
        modifier = modifier
            .padding(vertical = 10.dp)
            .height(30.dp)
            .border(
                width = 1.dp,
                borderColor,
                RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ){
        content(textColor)
    }
}


@Composable
fun NumberAndLabel(number: Int, label: String){
    Column {
        Text(number.toString())
        //Spacer(Modifier.height(5.dp))
        Text(label)
    }
}

@Composable
fun MyProfileScreenTopBar(){
    Row(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background).padding(vertical = 20.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            modifier = Modifier.clickableNoEffect {

            },
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(painterResource(R.drawable.ic_lock_24), null, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(8.dp))
            Text(Dummy.myProfile.username, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.width(8.dp))
            Icon(painterResource(R.drawable.ic_arrow_down_24), contentDescription = null, modifier = Modifier.size(10.dp))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.CenterVertically) {

            Icon(painterResource(R.drawable.ic_threads_24), contentDescription = null)
            Icon(painterResource(R.drawable.ic_new_post_24), contentDescription = null)
            Icon(painterResource(R.drawable.ic_more_horiz_24), contentDescription = null, modifier = Modifier.size(22.dp))
        }
    }


}