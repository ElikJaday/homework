package dev.elvir.user_search_list_impl

import android.content.Context
import dev.elvir.core.feature_component.DependencyManager
import dev.elvir.user_detail_screen_api.UserDetailApi
import dev.elvir.user_search_list_api.UserSearchListApi
import dev.elvir.user_search_list_impl.di.DaggerSearchListComponent
import dev.elvir.user_search_list_impl.di.DaggerSearchListComponent_SearchInnerComponent
import dev.elvir.user_search_list_impl.di.SearchListComponent
import dev.elvir.user_search_list_impl.ui.UserSearchListActivity

object ComponentManager {

    private lateinit var searchListComponent: SearchListComponent

    fun plusSearchListComponent(context: Context): SearchListComponent {
        return DaggerSearchListComponent.builder()
            .build()
            .also { searchListComponent = it }
    }


    fun getSearchListComponent(context: Context): SearchListComponent {
        return if (this::searchListComponent.isInitialized) {
            searchListComponent
        } else {
            plusSearchListComponent(context)
        }
    }

    fun inject(activity: UserSearchListActivity) {
        DaggerSearchListComponent_SearchInnerComponent.builder()
            .userSearchListApi(
                DependencyManager.getFeatureDependecy(
                    activity,
                    UserSearchListApi::class.java
                )
            )
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

