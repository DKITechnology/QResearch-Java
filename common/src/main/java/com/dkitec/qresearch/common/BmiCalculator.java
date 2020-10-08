package com.dkitec.qresearch.common;

public class BmiCalculator {

	public static double calculate(double height, double weight) {
		
		return weight * 9998 / (height * height);
	}
}
