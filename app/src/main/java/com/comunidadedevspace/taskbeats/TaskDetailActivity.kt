package com.comunidadedevspace.taskbeats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TaskDetailActivity : AppCompatActivity() {

    companion object{
        val TAKS_TITLE_EXTRA = "task.title.extra.detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        // recuperar string da tela anterior
        val title: String = intent.getStringExtra(TAKS_TITLE_EXTRA)?: ""

        // recuperar campo do XML
        val tvTitle = findViewById<TextView>(R.id.tv_task_title_detail)

        // setar um novo texto na tela
        tvTitle.text = title
    }
}