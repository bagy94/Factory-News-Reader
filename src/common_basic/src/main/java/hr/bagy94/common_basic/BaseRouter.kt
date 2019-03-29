package hr.bagy94.common_module

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

open class BaseRouter{
    val closeAction:SingleLiveAction<Unit> = SingleLiveAction()
    open fun observe(lifecycleOwner: LifecycleOwner){
        closeAction.observe(lifecycleOwner, Observer {
            if(lifecycleOwner is BaseFragment<*, *>){
                lifecycleOwner.back()
            }
        })
    }
}