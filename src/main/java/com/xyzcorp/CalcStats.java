package com.xyzcorp;

import java.util.function.BiPredicate;
import java.util.function.IntPredicate;

public class CalcStats {

	private static final int LIMIT = 1000000;
	private static final String 
	   ARRAY_SIZE_SHOULD_BE_LESS_THAT_1000000 = 
	    "Array size should be less that 1000000";
	public static final String ARRAY_CANNOT_BE_NULL = 
			"Array cannot be null";
	public static final String ARRAY_IS_EMPTY = 
			"Array is empty";
	private int[] array;

	public CalcStats(int[] array) {
		if (array == null)
			throw new NullPointerException(ARRAY_CANNOT_BE_NULL);
		if (array.length > LIMIT)
			throw new IllegalArgumentException
			(ARRAY_SIZE_SHOULD_BE_LESS_THAT_1000000);
		this.array = array;
	}

	private int calculate(BiPredicate<Integer, Integer> biPredicate) {
		int length = array.length;
		if (length == 0)
			throw new IllegalStateException(ARRAY_IS_EMPTY);
		int result = array[0];
		for (int i = 1; i < length; i++) {
			if (biPredicate.test(array[i], result))
				result = array[i];
		}
		return result;
	}

	public int getMinimum() {
		return calculate((next, result) -> next < result);
	}

	public int getMaximum() {
	    return calculate((next, result) -> next > result);
	}

	public int getSize() {
		return array.length;
	}

	public double getAverage() {
		if (array.length == 0)
			throw new IllegalStateException(ARRAY_IS_EMPTY);
		double sum = 0.0;
		for (int i = 0; i < getSize(); i++) {
			sum += array[i];
		}
		return sum / getSize();
	}
}
