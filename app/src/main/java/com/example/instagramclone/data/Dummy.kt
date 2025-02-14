package com.example.instagramclone.data

import androidx.annotation.DrawableRes
import com.example.instagramclone.R
import okhttp3.internal.immutableListOf
import java.util.UUID
import kotlin.math.min


data class Post(
    @DrawableRes val profilePhoto: Int,
    @DrawableRes val photo: Int,
    val username: String,
    val description: String,
    val likes: Int,
    val comments: Int,
    val shares: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val id: String = UUID.randomUUID().toString()
)


data class MyProfile(
    @DrawableRes val profilePhoto: Int,
    val name: String,
    val username: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val posts: List<Post>,
    val reels: List<Post>,
    val taggedPosts: List<Post>,
)

data class Message(
    val user: Int,
    val message: String
)

object Dummy {
    val profilePhotos = immutableListOf(
        R.drawable.profile_photo_01, R.drawable.profile_photo_02, R.drawable.profile_photo_02, R.drawable.profile_photo_03, R.drawable.profile_photo_03, R.drawable.profile_photo_04, R.drawable.profile_photo_04, R.drawable.profile_photo_05,
        R.drawable.profile_photo_06, R.drawable.profile_photo_07, R.drawable.profile_photo_08, R.drawable.profile_photo_09, R.drawable.profile_photo_10, R.drawable.profile_photo_11, R.drawable.profile_photo_12, R.drawable.profile_photo_13,
        R.drawable.profile_photo_14, R.drawable.profile_photo_15, R.drawable.profile_photo_16, R.drawable.profile_photo_17, R.drawable.profile_photo_18, R.drawable.profile_photo_19,
    ).shuffled()

    private val photos = immutableListOf(
        R.drawable.post_01, R.drawable.post_02, R.drawable.post_03, R.drawable.post_04, R.drawable.post_05, R.drawable.post_06, R.drawable.post_07, R.drawable.post_08, R.drawable.post_09, R.drawable.post_10,
        R.drawable.post_11, R.drawable.post_12, R.drawable.post_13, R.drawable.post_14, R.drawable.post_15, R.drawable.post_16, R.drawable.post_17, R.drawable.post_18, R.drawable.post_19,
    ).shuffled()

    val reelThumbnails = immutableListOf(
        R.drawable.reel_thumbnail_01, R.drawable.reel_thumbnail_02, R.drawable.reel_thumbnail_03, R.drawable.reel_thumbnail_04, R.drawable.reel_thumbnail_05, R.drawable.reel_thumbnail_06, R.drawable.reel_thumbnail_07, R.drawable.reel_thumbnail_08,
        R.drawable.reel_thumbnail_09, R.drawable.reel_thumbnail_10, R.drawable.reel_thumbnail_11, R.drawable.reel_thumbnail_12, R.drawable.reel_thumbnail_13, R.drawable.reel_thumbnail_14, R.drawable.reel_thumbnail_15, R.drawable.reel_thumbnail_16,
        R.drawable.reel_thumbnail_17, R.drawable.reel_thumbnail_18, R.drawable.reel_thumbnail_19,
    )

    private val _usernames = listOf(
        "john_doe123", "sarah_smith", "alex_james", "emily_rose", "mike_williams", "lily_brown", "david_clark", "julia_davis", "ryan_moore", "isabella_white",
        "noah_johnson", "olivia_jones", "jackson_taylor", "chloe_miller", "lucas_anderson", "mia_lee", "elijah_garcia", "ava_harris", "logan_martinez"
    )
    private val _names = listOf(
        "John Doe", "Sarah Smith", "Alex James", "Emily Rose", "Mike Williams", "Lily Brown", "David Clark", "Julia Davis", "Ryan Moore", "Isabella White",
        "Noah Johnson", "Olivia Jones", "Jackson Taylor", "Chloe Miller", "Lucas Anderson", "Mia Lee", "Elijah Garcia", "Ava Harris", "Logan Martinez"
    )
    private val pairedList = _usernames.zip(_names)
    private val shuffledList = pairedList.shuffled()

    val usernames = shuffledList.map { it.first }
    val names = shuffledList.map { it.second }

    private val descriptions = immutableListOf(
        "Just another day in paradise 🌴", "Chasing dreams ✨", "Adventure awaits 🚀", "Sunsets and palm trees 🌅", "Life is better with friends ❤️", "Weekend vibes 🎉", "Exploring new places 🌍", "Coffee first, then the world ☕", "Lost in the moment ⏳", "Happiness is homemade 🍰",
        "Making memories 📸", "Feeling grateful 🙏", "Living my best life 💖", "Work hard, play hard 💪", "Good times, good vibes 😎", "Just keep swimming 🐟", "Smiling through it all 😊", "Life is short, live it 🌈", "Dream big, achieve bigger 🚀"
    ).shuffled()

    val notes = immutableListOf(
        "Just finished a great workout 💪 feeling unstoppable!", "Coffee, please! ☕", "Who else is obsessed with this song? 🎶", "Feeling blessed and grateful 🙏", "Excited for the weekend ahead 🌟", "Dreaming of vacation 🌴", "Trying to stay positive through everything 🌈", "Weekend plans: Netflix & chill 🍿",
        "Who else loves cozy rainy days? 🌧️", "Let’s get this week started! 🚀", "Good things take time ✨", "Can’t stop thinking about the latest book I read 📚", "Catch me at the beach today 🌊", "Trying something new today 🔥", "Chasing my dreams, one step at a time ✨", "Work hard, play harder 💼🎉",
        "The best is yet to come 🌟", "Always make time for what matters most 💖", "The journey is just as important as the destination 🚗💨", "Feeling like a million bucks today 💰", "Doing what I love and loving what I do 😍", "Grateful for the little things 🙌", "Keep your head up and your heart strong ❤️", "Who else is excited for the new season of their favorite show? 📺",
        "Life is tough, but so are you 💪", "Taking a break to recharge 🌱", "Making memories with the best people ✨", "Stay curious and keep exploring 🌍", "Life's too short for bad vibes ✌️", "Just living my best life 🙌"
    ).shuffled()

    private val bios = immutableListOf(
        "✨ Living life one adventure at a time 🌍", "📚 Bookworm\n🌱 Plant lover\n☕ Coffee addict", "💫 Creating my own path in this beautiful chaos ✨", "🌸 Just a girl with big dreams and a heart full of love ❤️",
        "👟 Runner\n🌄 Nature enthusiast\n🎧 Music lover", "🌻 Spread kindness like confetti ✨", "📝 Writing my own story, one chapter at a time 📖", "🎮 Gamer\n🍕 Pizza lover\n✈️ Wanderlust explorer",
        "🔥 Chasing my dreams, no limits 🚀", "🌈 Believer in magic\ncoffee\nand good vibes only ✨"
    ).shuffled()


    val posts = List(19) { index ->
        Post(
            profilePhotos[index], photos[index],
            usernames[index], descriptions[index],
            (1..999).random(), (1..99).random(), (1..999).random(),
            (2022..2025).random(), (1..12).random(), (1..28).random()
        )
    }.shuffled()

    val reels = List(19){index->
        Post(
            profilePhotos[index], reelThumbnails[index],
            usernames[index], descriptions[index],
            (1..999).random(), (1..99).random(), (1..999).random(),
            (2022..2025).random(), (1..12).random(), (1..28).random()
        )
    }.shuffled()

    private val photosDividerIndex1 = (2..8).random()
    private val photosDividerIndex2 = (photosDividerIndex1..13).random()

    val postsGroup1 = posts.subList(0, photosDividerIndex1)
    val postsGroup2 = posts.subList(photosDividerIndex1, photosDividerIndex2)
    val postsGroup3 = posts.subList(photosDividerIndex2, photos.size)

    private val index = (0..<min(names.size, usernames.size)).random()

    val myProfile = MyProfile(
        profilePhotos.random(),
        name = names[index],
        username = usernames[index],
        bios.random(),
        (1..999).random(),
        (1..999).random(),
        postsGroup1.shuffled().take((1..postsGroup1.size).random()),
        reels.shuffled().take((1..reels.size).random()),
        postsGroup2.shuffled().take((0..postsGroup2.size).random())
    )

    private fun createRandomMessage(): String{
        val messageType = (0..9).random()
        val words = when(messageType){
            in 0..4-> (1..6).random()
            in 5..8-> (7..18).random()
            else-> (20..35).random()
        }
        var message = ""
        for (i in 1..words){
            val wordLength = (2..8).random()
            for (j in 1..wordLength){
                message += ('a'..'z').random()
            }
            message += " "
        }
        message = message.trim()

        return message
    }

    fun randomMessages(count: Int): List<Message>{
        val messages = mutableListOf<Message>()
        for (i in 1..count){
            messages.add(
                Message(
                    (0..1).random(),
                    createRandomMessage()
                )
            )
        }

        return messages
    }
}
