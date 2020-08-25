package dev.elvir.homeworkforcinemood.di

import dagger.Component
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@Component(
    modules = [
    ]
)
@AppScope
interface AppComponent {

}