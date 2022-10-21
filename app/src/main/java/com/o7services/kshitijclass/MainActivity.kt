package com.o7services.kshitijclass

import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.o7services.kshitijclass.databinding.ActivityMainBinding
import com.o7services.kshitijclass.databinding.LayoutDialogBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var studentDb: StudentDb
    lateinit var listAdapter: ListAdapter
    var list = ArrayList<StudentTable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        studentDb = StudentDb.getDatabase(this)
        listAdapter = ListAdapter(list)
        binding.listView.adapter = listAdapter
        binding.activity = this

        getData()
    }
    fun onClick(){
        val dialog = Dialog(this)
        var dialogBinding = LayoutDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.etName.setText(binding.name)
        dialogBinding.button.setOnClickListener{
            binding.name = dialogBinding.etName.text.toString()
            saveInDb(binding.name?:"")

            System.out.print(" data"+dialogBinding.etName.text.toString())
            dialog.dismiss()
        }
        dialog.show()
    }

    fun saveInDb(name: String){
        class save : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                studentDb.studentDao().insertStudent(StudentTable(name = binding.name))

                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                getData()

                Toast.makeText(this@MainActivity, "data saved", Toast.LENGTH_LONG).show()
            }
        }
        save().execute()
    }

    fun getData(){
        list.clear()
        class save : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
               list.addAll(studentDb.studentDao().getStudent())

                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                listAdapter.notifyDataSetChanged()
            }
        }
        save().execute()
    }
}