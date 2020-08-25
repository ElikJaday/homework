package dev.elvir.core.base

class BaseMvp {

    interface Presenter<in T> {
        fun attach(view: T)
        fun detach()
    }

    interface View {

    }
}