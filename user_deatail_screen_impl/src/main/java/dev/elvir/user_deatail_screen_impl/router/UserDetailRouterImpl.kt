package dev.elvir.user_deatail_screen_impl.router

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dev.elvir.user_deatail_screen_impl.ui.UserDetailActivity
import dev.elvir.user_detail_screen_api.UserDetailRouter
import dev.elvir.user_detail_screen_api.model.User

class UserDetailRouterImpl : UserDetailRouter {

    override fun startUserDetailScreen(context: Context, user: User, bundle: Bundle) {
        val intent = Intent(context, UserDetailActivity::class.java)
        intent.putExtra("obj", user)
        context.startActivity(
            intent, bundle
        )
    }

}