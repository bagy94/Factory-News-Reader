package hr.bagy94.common_module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

abstract class BaseFullScreenFragment<VIEWMODEL: BaseViewModel<*>, BINDING: ViewDataBinding>:BaseFragment<VIEWMODEL,BINDING>(){

    var mUIControlsVisibility = true
        protected set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState).apply { this?.setOnClickListener{toggleSystemUI()} }
    }

    protected open fun showSystemUI(){
        mViewBinding.root.systemUiVisibility = 0
        mUIControlsVisibility = true
    }
    protected open fun hideSystemUI(){
        mViewBinding.root.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        mUIControlsVisibility = false
    }
    protected open fun toggleSystemUI(){
        if (mUIControlsVisibility){
            hideSystemUI()
        }else{
            showSystemUI()
        }
    }
}