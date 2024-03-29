package com.comunidadedevspace.taskbeats.presentation

import android.app.Application
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.comunidadedevspace.taskbeats.TaskBeatsApplication
import com.comunidadedevspace.taskbeats.data.Task
import com.comunidadedevspace.taskbeats.data.TaskDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    private val taskDao: TaskDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    fun execute(taskAction: TaskAction){
        when (taskAction.actionType) {
            ActionType.DELETE.name -> deleteFromDataBase(taskAction.task!!)
            ActionType.CREATE.name -> insertIntoDataBase(taskAction.task!!)
            ActionType.UPDATE.name -> updateIntoDataBase(taskAction.task!!)
        }
    }

    private fun deleteFromDataBase(task: Task) {
        viewModelScope.launch(dispatcher) {
            taskDao.delete(task)
        }
    }

    private fun insertIntoDataBase(task: Task) {
        viewModelScope.launch(dispatcher) {
            taskDao.insert(task)
        }
    }


    private fun updateIntoDataBase(task: Task) {
        viewModelScope.launch(dispatcher) {
            taskDao.update(task)
        }
    }

    private fun deleteAllFromDataBase() {
        viewModelScope.launch(dispatcher) {
            taskDao.deleteAll()
        }
    }

    companion object{
         fun getVMFactory(application: Application): ViewModelProvider.Factory {

             val dataBaseInstance = (application as TaskBeatsApplication).getAppDataBase()

             val dao = dataBaseInstance.taskDao()

             return object : ViewModelProvider.Factory{
                 override fun <T : ViewModel> create(modelClass: Class<T>): T {
                     return TaskDetailViewModel(dao) as T
                 }
             }
         }
    }
}