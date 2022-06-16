package cc.bear3.lec

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import cc.bear3.lec.kernel.R

/**
 *
 * @author TT
 * @since 2020-12-8
 */
abstract class LecActivity : AppCompatActivity(), ILecPage {

    override lateinit var root: FrameLayout

    override var loadingView: View? = null
    override var errorView: View? = null

    override val lecState = MutableLiveData(Lec.Content)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_lec)
        root = findViewById(R.id.fl_layout_lec_root)

        root.addView(onCreateContentView(), getAttachParams())

        defaultObserverLecState()
    }

    override fun getLifecycleOwner(): LifecycleOwner {
        return this
    }
}