package hr.bagy94.common_module

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveAction<T>:MutableLiveData<T> (){

    private val mPending:AtomicBoolean = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
        if(hasActiveObservers()){
            Log.d("SingleLiveAction","hasObservers()")
        }
        super.observe(owner, Observer{ data ->
            if (mPending.compareAndSet(true,false)){
                observer.onChanged(data)
            }
        })
    }

    @MainThread
    override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call(){
        value = null
    }
}