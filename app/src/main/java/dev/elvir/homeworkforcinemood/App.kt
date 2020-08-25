package dev.elvir.homeworkforcinemood

import android.app.Application
import dev.elvir.core.feature_component.ComponentManager as CoreComponent

class App : Application(), CoreComponent {

    lateinit var componentManager: ComponentManager

    override fun onCreate() {
        super.onCreate()
        if (!this::componentManager.isInitialized) {
            createMainComponentManager()
        }
    }

    override fun <T> getDependency(key: Class<T>): T = componentManager.componentBuilder(key, this)

    private fun createMainComponentManager(): ComponentManager =
        ComponentManager()
            .apply { plusAppComponent() }
            .also { componentManager = it }
}
