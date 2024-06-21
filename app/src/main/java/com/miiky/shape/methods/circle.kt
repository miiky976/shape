package com.miiky.shape.methods

import com.miiky.shape.models.Cords
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sin
import kotlin.math.sqrt

fun ecircle(c: Cords, thick: Cords): MutableList<Cords> {
	val dots = (thick.x * 2) + (thick.y * 2)
	val angle = 360f / dots.toFloat()
	val list: MutableList<Cords> = mutableListOf(c)
	for (i in (0..dots)) {
		val x = c.x + (cos(i.toFloat()) * sqrt(thick.x.toFloat()))
		val y = c.y + (sin(i.toFloat()) * sqrt(thick.y.toFloat()))
		list += Cords(round(x).toInt(), round(y).toInt())
	}
	return list.toSet().toMutableList()
}

fun circle(center: Cords, thick: Cords): MutableList<Cords> {
	var cx = center.x.toFloat()
	var cy = center.y.toFloat()

	if (thick.x % 2 == 0) {
		cx += 0.5f
	}
	if (thick.y % 2 == 0){
		cy += 0.5f
	}

	val list: MutableList<Cords> = mutableListOf(center,
		Cords(round(cx + thick.x/2f).toInt(), cy.toInt()),
		Cords(round(cx - thick.x/2f).toInt(), cy.toInt()),
		Cords(cx.toInt(), round(cy + thick.y/2f).toInt()),
		Cords(cx.toInt(), round(cy - thick.y/2f).toInt()))
	// this works by octant's
	val halves = Cords(thick.x / 2, thick.y / 2)
	val sixY = (thick.y / 6f).toInt() + 1
	val sixX = (thick.x / 6f).toInt()
	// first
	for (i in (0..sixY * 2)) {
		val x = sqrt((1f - i.toDouble().pow(2) / thick.y) * thick.x)
		list += Cords(round(x).toInt() + cx.toInt(), i + cy.toInt())
	}
	for (i in (sixX..sixX * 4)) {
		val y = sqrt((1f - i.toDouble().pow(2) / thick.x) * thick.y)
		list += Cords(i + cx.toInt(), round(y).toInt() + cy.toInt())
	}
	for (i in (-sixY * 4 .. -sixY)) {
		val x = -sqrt((1f - i.toDouble().pow(2) / thick.y) * thick.x)
		list += Cords(round(x).toInt() + cx.toInt(), i + cy.toInt())
	}
	for (i in (-sixX * 4 .. -sixX)) {
		val y = -sqrt((1f - i.toDouble().pow(2) / thick.x) * thick.y)
		list += Cords(i + cx.toInt(), round(y).toInt() + cy.toInt())
	}
	for (i in (sixY * 2..0)) {
		val x = -sqrt((1f - i.toDouble().pow(2) / thick.y) * thick.x)
		list += Cords(round(x).toInt() + cx.toInt(), i + cy.toInt())
	}

	return list
}