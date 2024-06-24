package com.miiky.shape.viewModels

import androidx.lifecycle.ViewModel
import com.miiky.shape.models.Cords

class LineViewModel : ViewModel() {
	private val _start : Cords = Cords(-10, -10)
	private val _end : Cords = Cords(20, 5)
}