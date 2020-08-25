package dev.elvir.homeworkforcinemood

import android.content.Context
import dev.elvir.homeworkforcinemood.di.AppComponent
import dev.elvir.homeworkforcinemood.di.DaggerAppComponent
import dev.elvir.user_detail_screen_api.UserDetailApi
import dev.elvir.user_search_list_api.UserSearchListApi
import dev.elvir.user_search_list_impl.ComponentManager as SearchComponentManager
import dev.elvir.user_deatail_screen_impl.ComponentManager as DetailComponentManager


/**
 * @author Elvir
 * Created by ElikJaday on 07/08/2020.
 * @see
 * Visit my GitHub
 * https://github.com/ElikJaday
 */
class ComponentManager {

    private lateinit var appComponent: AppComponent

    private val searchListComponent by lazy {
        SearchComponentManager
    }
    private val userDetailComponent by lazy {
        DetailComponentManager
    }

    fun plusAppComponent(): AppComponent = DaggerAppComponent
        .builder()
        .build().also { appComponent = it }


    fun <T> componentBuilder(
        api: Class<T>,
        context: Context
    ): T {
        return when (api) {
           UserSearchListApi::class.java -> searchListComponent.getSearchListComponent(context)
            UserDetailApi::class.java -> userDetailComponent.getUserDetailComponent(context)
            else -> throw RuntimeException("component API not found ")
        } as T
    }


}