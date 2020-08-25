package dev.elvir.user_deatail_screen_impl

import android.content.Context
import dev.elvir.core.feature_component.DependencyManager
import dev.elvir.user_deatail_screen_impl.di.DaggerUserDetailComponent
import dev.elvir.user_deatail_screen_impl.di.DaggerUserDetailComponent_UserDetailInnerComponent
import dev.elvir.user_deatail_screen_impl.di.UserDetailComponent
import dev.elvir.user_deatail_screen_impl.ui.UserDetailActivity
import dev.elvir.user_detail_screen_api.UserDetailApi

object ComponentManager {

    private lateinit var userDetailComponent: UserDetailComponent

    fun plusUserDetailComponent(context: Context): UserDetailComponent {
        return DaggerUserDetailComponent.builder()
            .build()
            .also { userDetailComponent = it }
    }


    fun getUserDetailComponent(context: Context): UserDetailComponent {
        return if (this::userDetailComponent.isInitialized) {
            userDetailComponent
        } else {
            plusUserDetailComponent(context)
        }
    }

    fun inject(activity: UserDetailActivity) {
        DaggerUserDetailComponent_UserDetailInnerComponent.builder()
            .userDetailApi(
                DependencyManager.getFeatureDependecy(
                    activity,
                    UserDetailApi::class.java
                )
            )
            .build()
            .inject(activity)
    }


}