package com.xyzcorp;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalcStatsJava8Test {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testMinimumNoItemInAnArray() {
	     thrown.expect(IllegalArgumentException.class);
	     thrown.expectMessage("Array is empty");
		// Create a new instance with the array
		CalcStatsJava8 calcStats = new CalcStatsJava8(new int[] {});
		calcStats.getMinimum();
		
	}

	@Test
	public void testMinimumWithOneItemArray() {
		CalcStatsJava8 calcStats = new CalcStatsJava8(new int[] { 3 });
		assertEquals(3, calcStats.getMinimum());
	}
	
	@Test
	public void testMinimumWithTwoItemArray() {
		CalcStatsJava8 calcStats = new CalcStatsJava8(new int[] { 3, 4 });
		assertEquals(3, calcStats.getMinimum());
	}
}
