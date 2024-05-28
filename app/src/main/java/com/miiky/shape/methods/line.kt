package com.miiky.shape.methods

import com.miiky.shape.models.Cords
import kotlin.math.abs
import kotlin.math.round

fun line(a: Cords, b: Cords): MutableList<Cords> {
	val ax = b.x.toFloat() - a.x.toFloat()
	val ay = b.y.toFloat() - a.y.toFloat()
	val m = ay / ax
	val nList: MutableList<Cords> = mutableListOf()
	if (abs(ay) > abs(ax)) {
		if (b.y > a.y) {
			for (i in (a.y..b.y)) {
				val x = lineX(m, i, a)
				nList += Cords(round(x).toInt(), i)
			}
		} else {
			for (i in (b.y..a.y)) {
				val x = lineX(m, i, a)
				nList += Cords(round(x).toInt(), i)
			}
			nList.reverse()
		}
		return nList
	} else {
		// Normal way
		if (b.x > a.x) {
			for (i in (a.x..b.x)) {
				val y = lineY(m, i, a)
				nList += Cords(i, round(y).toInt())
			}
		} else {
			for (i in (b.x..a.x)) {
				val y = lineY(m, i, a)
				nList += Cords(i, round(y).toInt())
			}
			nList.reverse()
		}
		return nList
	}
}

fun lineY(m: Float, x: Int, cord: Cords): Float {
	return m * (x.toFloat() - cord.x.toFloat()) + cord.y.toFloat()
}

fun lineX(m: Float, x: Int, cord: Cords): Float {
	return (x.toFloat() - cord.y.toFloat()) / m + cord.x.toFloat()
}
