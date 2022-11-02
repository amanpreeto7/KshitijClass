package com.o7services.kshitijclass

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.o7services.kshitijclass.databinding.ActivitySpinnerBinding
import com.o7services.kshitijclass.databinding.DialogListBinding

class SpinnerActivity : AppCompatActivity() {
    private  val TAG = "SpinnerActivity"
    lateinit var arrayAdapter: ArrayAdapter<String>
    var studentArray = arrayListOf<StudentTable>()
    var shownStudentArray = arrayListOf<StudentTable>()
    var spinnerArray = mutableListOf<String>("Select","one", "two", "three")
    lateinit var binding : ActivitySpinnerBinding
    lateinit var adapter: ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding?.isShow = false
        studentArray.add(StudentTable(name = "name", id = 1))
        studentArray.add(StudentTable(name = "qwerty", id = 2))
        studentArray.add(StudentTable(name = "uiop", id = 3))
        studentArray.add(StudentTable(name = "qwerty123", id = 4))
        shownStudentArray.addAll(studentArray)
        adapter = ListAdapter(shownStudentArray)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.spinner_values))
        binding.spinner.adapter = arrayAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
               Log.w(TAG, "in position $position")
               Toast.makeText(this@SpinnerActivity,
                   " in item selected ${studentArray[position]}",
                   Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }

        //custom dialog
        binding.btnSelect.setOnClickListener {
            binding.isShow = !(binding?.isShow?:false)
            var dialog = Dialog(this@SpinnerActivity)
            var dialogBinding = DialogListBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
          //  dialog.show()
            dialogBinding.listView.adapter = adapter
            dialogBinding.etSearch.doOnTextChanged { text, start, before, count ->

                if((text?.length?:0)>3){
                    shownStudentArray.clear()
                    adapter.notifyDataSetChanged()
                    text?.let {
                        shownStudentArray.addAll(studentArray.filter { element-> element.name?.contains(text) == true })
                    }
                    adapter.notifyDataSetChanged()
                }else if((text?.length?:0)== 0){
                    shownStudentArray.clear()
                    shownStudentArray.addAll(studentArray)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}