package cc.bear3.lec

import androidx.annotation.DrawableRes

class Lec {
    sealed interface State

    object Loading : State

    class Error(
        @DrawableRes val drawableResId: Int = 0,
        val message: String? = null,
        val obj1: Any? = null,
        val obj2: Any? = null
    ) : State

    object Content : State
}