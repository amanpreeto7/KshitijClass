package com.o7services.kshitijclass

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(student: StudentTable)

    @Query("SELECT * FROM StudentTable")
    fun getStudent():List<StudentTable>
}