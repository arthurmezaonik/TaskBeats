package com.comunidadedevspace.taskbeats.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Query("SELECT * FROM task")
    fun getAll() : LiveData<List<Task>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    @Query("DELETE FROM task")
    fun deleteAll()

    @Delete
    fun delete(task: Task)
}