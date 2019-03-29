package hr.bagy94.common_module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<VIEWMODEL: BaseViewModel<*>, BINDING:ViewDataBinding>:Fragment() {
    abstract val provideViewId:Int
    abstract val provideViewModelClass:Class<VIEWMODEL>
    protected  lateinit var mViewModel:VIEWMODEL
    protected lateinit var mViewBinding:BINDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        mViewModel.router.observe(this)
        arguments?.let{onParseArguments(it)}
    }
    protected open fun initViewModel(){
        mViewModel = ViewModelProviders.of(this).get(provideViewModelClass)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        onDataBinding(inflater,container)
        return mViewBinding.root
    }

    fun back(){
        findNavController().navigateUp()
    }

    open fun onParseArguments(arguments:Bundle){

    }
    open fun onDataBinding(inflater: LayoutInflater, container: ViewGroup?){
        mViewBinding = DataBindingUtil.inflate(inflater, provideViewId, container, false)
        mViewBinding.lifecycleOwner  = this
    }

}