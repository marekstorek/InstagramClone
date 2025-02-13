package com.example.instagramclone.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.instagramclone.Post
import com.example.instagramclone.ReelSuggestion
import com.example.instagramclone.StoriesBar
import com.example.instagramclone.ThreadsBlock
import com.example.instagramclone.TopBar
import com.example.instagramclone.data.Dummy

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier, pagerState: PagerState, navController: NavHostController) {
    LazyColumn(modifier = modifier) {
        stickyHeader {
            TopBar(pagerState)
        }
        item {
            StoriesBar()
        }
        item{
            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), thickness = 0.5.dp)
        }
        items(Dummy.postsGroup1, key = {it.id}){
            Post(it)
        }
        item{
            ThreadsBlock()
        }
        items(Dummy.postsGroup2, key = {it.id}){
            Post(it)
        }
        item {
            ReelSuggestion(navController = navController)
        }
        items(Dummy.postsGroup3, key = {it.id}){
            Post(it)
        }

    }
}