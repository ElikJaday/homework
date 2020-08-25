package dev.elvir.user_deatail_screen_impl.di

import dagger.Component
import dagger.Module
import dagger.Provides
import dev.elvir.user_deatail_screen_impl.router.UserDetailRouterImpl
import dev.elvir.user_deatail_screen_impl.ui.UserDetailActivity
import dev.elvir.user_detail_screen_api.UserDetailApi
import dev.elvir.user_detail_screen_api.UserDetailRouter
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserDetailScope


@Component(
    modules = [
        UserDetailModule::class
    ]
)
@UserDetailScope
interface UserDetailComponent : UserDetailApi {
    @Component(
        dependencies = [
            UserDetailApi::class
        ]
    )
    @UserDetailScope
    interface UserDetailInnerComponent {
        fun inject(activty: UserDetailActivity)
    }
}


@Module
class UserDetailModule {

    @Provides
    @UserDetailScope
    fun provideUserDetailRouter(): UserDetailRouter = UserDetailRouterImpl()

}

