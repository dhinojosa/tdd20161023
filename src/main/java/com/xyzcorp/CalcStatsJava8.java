package com.xyzcorp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CalcStatsJava8 {

	private int[] is;

	public CalcStatsJava8(int[] is) {
		this.is = is;
		
	}

	public int getMinimum() {
		if (is.length == 0) 
			throw new IllegalArgumentException("Array is empty");
		return Arrays.stream(is).min().getAsInt();
	}

}
