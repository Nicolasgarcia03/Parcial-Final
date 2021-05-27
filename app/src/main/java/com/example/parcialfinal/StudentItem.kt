package com.example.parcialfinal

import com.example.parcialfinal.databinding.ItemStudentBinding
import com.xwray.groupie.databinding.BindableItem

class StudentItem(val student: String): BindableItem<ItemStudentBinding>(){
    override fun bind(viewBinding: ItemStudentBinding, position: Int) {
        viewBinding.studentTextView.text = student
    }

    override fun getLayout(): Int {
        return R.layout.item_student
    }
}