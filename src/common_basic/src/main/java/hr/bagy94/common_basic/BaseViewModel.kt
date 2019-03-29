package hr.bagy94.common_module

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel<ROUTER: BaseRouter>(application: Application, val router:ROUTER):AndroidViewModel(application) {

    fun close(){
        router.closeAction.call()
    }
}