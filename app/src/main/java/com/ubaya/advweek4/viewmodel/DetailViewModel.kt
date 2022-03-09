package com.ubaya.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import model.Student

class DetailViewModel {
    val studentLd = MutableLiveData<Student>()

    fun fetch() {
        val student1 = Student("16055","Nonie","1998/03/28","5718444778", "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        studentLd.value = student1
    }
}