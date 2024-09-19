package com.miiky.shape.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miiky.shape.methods.line
import com.miiky.shape.models.Cords

class LineViewModel : ViewModel() {
	private val _start: MutableLiveData<Cords> = MutableLiveData(Cords(-10, -10))
	private val _end: MutableLiveData<Cords> = MutableLiveData(Cords(10, 5))
	private var _list: MutableLiveData<List<Cords>> = MutableLiveData(listOf())

	val start: LiveData<Cords> = _start
	val end: LiveData<Cords> = _end
	var list: LiveData<List<Cords>> = _list

	fun createLineList() {
	}

}
