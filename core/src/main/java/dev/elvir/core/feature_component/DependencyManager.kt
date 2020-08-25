package dev.elvir.core.feature_component

import android.content.Context

object DependencyManager {

    @JvmStatic
    fun <T> getFeatureDependecy(
        context: Context,
        api: Class<T>
    ): T {
        val componentManager = context.applicationContext as ComponentManager
        return componentManager.getDependency(api)
    }
}
