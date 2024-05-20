package com.miiky.shape.methods

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.miiky.shape.models.Cords
import kotlin.math.abs
import kotlin.math.round

fun Line(a: Cords, b: Cords): MutableList<Cords> {
	var start = a
	var end = b
	val list: MutableList<Cords> = mutableListOf(a)
	// for the moment i only need to fit them to horizontally lines
	val ax = b.x.toFloat() - a.x.toFloat()
	val ay = b.y.toFloat() - a.y.toFloat()
	if (abs(ay) > abs(ax)) {
		// TODO Handle the inverse append
		start = Cords(a.y, a.x)
		end = Cords(b.y, b.x)
	} else {
		// Normal way
		val m = ay / ax
		val nList: MutableList<Cords> = mutableListOf()
		for (i in (start.x..end.x)) {
			val y: Float = m * (i.toFloat() - start.x.toFloat()) + start.y.toFloat()
			Log.v("Count", (i .. round(y).toInt()).toString())
			nList += Cords(i, round(y).toInt())
		}
		Log.v("Count", nList.size.toString())
		return nList
	}
	return list
}