package dev.elvir.core.base

import android.content.Context

abstract class BaseComponentManager {
    abstract fun getComponent()
    abstract fun plusComponent(context: Context)
    abstract fun releaseComponent()
}