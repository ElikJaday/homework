package dev.elvir.core.feature_component

interface ComponentManager {
    fun <T> getDependency(key: Class<T>): T
}