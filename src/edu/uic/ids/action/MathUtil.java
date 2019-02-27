package edu.uic.ids.action;

public class MathUtil {
	public static double round(double value, double precision) {
		return Math.round(value * precision)/precision;
		}
}
