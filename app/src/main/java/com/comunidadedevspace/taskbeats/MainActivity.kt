package com.comunidadedevspace.taskbeats

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    // Kotlin
    private var taskList = arrayListOf(
        Task(0,"Academia", "Treino de corrida"),
        Task(1,"Mercado", "Comprar arroz e feijao"),
        Task(2,"DevSpace", "Estudar aula sobre cores no task beats"),
        Task(3,"Theo", "Brincar com o Theozinho")
    )

    private lateinit var ctnContent : LinearLayout
    // Adapter
    private val adapter = TaskListAdapter(::openTaskDetailView)

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            // pegando resultado
            val data = result.data
            val taskAction = data?.getSerializableExtra(TASK_ACTION_RESULT) as TaskAction
            val task: Task = taskAction.task

            val newList = arrayListOf<Task>()
                .apply {
                    addAll(taskList)
                }

            // removendo item da lista Kotlin
            newList.remove(task)

            if(newList.size == 0){
                ctnContent.visibility = View.VISIBLE
            }

            // atualizar o adapter
            adapter.submitList(newList)
            taskList = newList
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ctnContent = findViewById(R.id.ctn_content)

        adapter.submitList(taskList)

        // RecyclerView
        val rvTasks : RecyclerView = findViewById(R.id.rv_tasks_list)
        rvTasks.adapter = adapter
    }

    private fun openTaskDetailView(task: Task){
        val intent = TaskDetailActivity.start(this, task)

        startForResult.launch(intent)
    }
}

sealed class ActionType: Serializable{
    object DELETE: ActionType()
    object UPDATE: ActionType()
    object CREATE: ActionType()
}

data class TaskAction(
    val task:Task,
    val actionType: ActionType
): Serializable

const val TASK_ACTION_RESULT = "TASK_ACTION_RESULT"