package hr.bagy94.common_basic

import android.os.AsyncTask

class SimpleAsyncTask(private val task:()->Unit):AsyncTask<Unit,Unit,Unit>(){
    override fun doInBackground(vararg params: Unit?) {
        task()
    }
}

fun async(task:()->Unit){
    SimpleAsyncTask(task).execute()
}