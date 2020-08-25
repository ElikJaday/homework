package dev.elvir.user_search_list_impl.di

import dagger.Component
import dev.elvir.user_detail_screen_api.UserDetailApi
import dev.elvir.user_search_list_api.UserSearchListApi
import dev.elvir.user_search_list_impl.ui.UserSearchListActivity
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SearchListScope

@Component()
@SearchListScope
interface SearchListComponent : UserSearchListApi {

    @Component(
        modules = [
            NetworkModule::class
        ],
        dependencies = [
            UserSearchListApi::class,
            UserDetailApi::class]
    )
    @SearchListScope
    interface SearchInnerComponent {
        fun inject(activity: UserSearchListActivity)

    }

}

