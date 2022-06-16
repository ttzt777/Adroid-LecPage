package cc.bear3.lec

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

/**
 *
 * @author TT
 * @since 2021-6-2
 */
interface ILecPage {
    val lecState: MutableLiveData<out Lec.State>

    var root: FrameLayout

    var loadingView: View?
    var errorView: View?

    fun getLifecycleOwner(): LifecycleOwner

    fun defaultObserverLecState() {
        lecState.observe(getLifecycleOwner()) {
            when (it) {
                is Lec.Loading -> showLoadingLayout()
                is Lec.Error -> showErrorLayout()
                is Lec.Content -> showContentLayout()
                else -> {
                }
            }
        }
    }

    fun changeLecStatus(lecState: Lec.State) {
        this.lecState.value = lecState
    }

    fun showLoadingLayout() {
        dismissErrorLayout()

        val topMargin = getTopMargin(lecState.value!!)

        if (loadingView == null) {
            loadingView = onCreateLoadingView().apply { isClickable = true }

            val params = getAttachParams().apply {
                this.topMargin = topMargin
            }
            root.addView(loadingView, params)
        } else {
            loadingView?.let {
                it.layoutParams = (it.layoutParams as FrameLayout.LayoutParams).apply {
                    this.topMargin = topMargin
                }
            }
        }

        loadingView?.let {
            onLoadingViewCreated(it)
        }
    }

    fun dismissLoadingLayout() {
        loadingView?.let {
            onDestroyLoadingView(it)
            root.removeView(it)
        }
        loadingView = null
    }

    fun showErrorLayout() {
        dismissLoadingLayout()

        val topMargin = getTopMargin(lecState.value!!)

        if (errorView == null) {
            errorView = onCreateErrorView().apply { isClickable = true }

            val params = getAttachParams().apply {
                this.topMargin = topMargin
            }
            root.addView(errorView, params)
        } else {
            errorView?.let {
                it.layoutParams = (it.layoutParams as FrameLayout.LayoutParams).apply {
                    this.topMargin = topMargin
                }
            }
        }

        errorView?.let {
            onErrorViewCreated(it)
        }
    }

    fun dismissErrorLayout() {
        errorView?.let {
            onDestroyErrorView(it)
            root.removeView(it)
        }
        errorView = null
    }

    fun showContentLayout() {
        dismissLoadingLayout()
        dismissErrorLayout()
    }

    fun onCreateContentView(): View

    fun onCreateLoadingView(): View

    fun onCreateErrorView(): View

    fun onLoadingViewCreated(loadingView: View) {

    }

    fun onErrorViewCreated(errorView: View) {

    }

    fun onDestroyLoadingView(loadingView: View){

    }

    fun onDestroyErrorView(errorView: View){

    }

    fun getTopMargin(lecState: Lec.State): Int {
        return 0
    }

    fun getAttachParams(): FrameLayout.LayoutParams {
        return FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }
}