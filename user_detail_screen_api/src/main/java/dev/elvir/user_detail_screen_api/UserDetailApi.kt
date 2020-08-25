package dev.elvir.user_detail_screen_api

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dev.elvir.user_detail_screen_api.model.User

interface UserDetailApi {

    fun provideUserDetailRouter() : UserDetailRouter
}

interface UserDetailRouter {

    fun startUserDetailScreen(context: Context, user: User, bundle: Bundle)

}

