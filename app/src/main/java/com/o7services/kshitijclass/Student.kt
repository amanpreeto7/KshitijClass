package com.o7services.kshitijclass

data class Student(
    var name: String? = null,
    var rollNo: Int ? = null,
    var sClass: String? = null
) {
    override fun toString(): String {
        return "$AdditionalName"
    }
    var AdditionalName : String ? = null
    get() {
        return name+"123"
    }
}