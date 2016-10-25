package com.xyzcorp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;

import org.assertj.core.data.Offset;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalcStatsTest {

	@Test
	public void testMinimumNoItemInAnArray() {
		// Create a new instance with the array
		CalcStats calcStats = new CalcStats(new int[] {});
		try {
			calcStats.getMinimum();
			fail("This line should not run");
		} catch (IllegalStateException ise) {
			assertEquals(ise.getMessage(), "Array is empty");
		}
	}

	@Test
	public void testMinimumWithOneItemArray() {
		CalcStats calcStats = new CalcStats(new int[] { 3 });
		assertEquals(3, calcStats.getMinimum());
	}

	@Test
	public void testMinimumWithOneItemArrayWithADifferentNumber() {
		CalcStats calcStats = new CalcStats(new int[] { 5 });
		assertThat(calcStats.getMinimum()).isEqualTo(5);
	}

	@Test
	public void testMinimumWithTwoItemArrayWithADifferentNumber() {
		CalcStats calcStats = new CalcStats(new int[] { 5, 1 });
		assertThat(calcStats.getMinimum()).isEqualTo(1);
	}

	@Test
	public void testMinimumWithTwoItemArrayWithADifferentCombination() {
		CalcStats calcStats = new CalcStats(new int[] { 5, 10 });
		assertThat(calcStats.getMinimum()).isEqualTo(5);
	}

	@Test
	public void testMinimumWithTwoItemArrayWithADifferentCombination2() {
		CalcStats calcStats = new CalcStats(new int[] { 10, 5 });
		assertThat(calcStats.getMinimum()).isEqualTo(5);
	}

	@Test
	public void testMinimumWithTwoItemArrayWithOnlyNegativeNumbers() {
		CalcStats calcStats = new CalcStats(new int[] { -13, -5, -10, -3, -1 });
		assertThat(calcStats.getMinimum()).isEqualTo(-13);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testNullValueToConstructor() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("Array cannot be null");
		new CalcStats(null);
	}

	// @Test
	// public void testMinimumWithOverflow() {
	// CalcStats calcStats = new CalcStats(new int[]{-13, -5,
	// Integer.MAX_VALUE + 1, -10, -3, -1});
	// assertThat(calcStats.getMinimum()).isEqualTo(-13);
	// }

	@Test
	public void testMaximumWithOneItemArray() {
		CalcStats calcStats = new CalcStats(new int[] { 3 });
		assertThat(calcStats.getMaximum()).isEqualTo(3);
	}

	@Test
	public void testMaximumWithOneItemArrayWithADifferentNumber() {
		CalcStats calcStats = new CalcStats(new int[] { 5 });
		assertThat(calcStats.getMaximum()).isEqualTo(5);
	}

	@Test
	public void testMaximumWithTwoItemArray() {
		CalcStats calcStats = new CalcStats(new int[] { 5, 10 });
		assertThat(calcStats.getMaximum()).isEqualTo(10);
	}

	@Test
	public void testMaximumWithEmptyArrayToConstructor() {
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage(CalcStats.ARRAY_IS_EMPTY);
		CalcStats cs = new CalcStats(new int[] {});
		cs.getMaximum();
	}

	@Test
	public void testMaximumWithTwoItemArrayWithOnlyNegativeNumbers() {
		CalcStats calcStats = new CalcStats(new int[] { -13, -5, -10, -3, -1 });
		assertThat(calcStats.getMaximum()).isEqualTo(-1);
	}

	@Test
	public void testSizeWithArrayOfNoElements() {
		CalcStats calcStats = new CalcStats(new int[] {});
		assertThat(calcStats.getSize()).isEqualTo(0);
	}

	@Test
	public void testSizeWithArrayOfOneElements() {
		CalcStats calcStats = new CalcStats(new int[] { 44 });
		assertThat(calcStats.getSize()).isEqualTo(1);
	}

	@Test
	public void testAverageWithArrayOfNoElements() {
		CalcStats calcStats = new CalcStats(new int[] {});
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage(CalcStats.ARRAY_IS_EMPTY);
		calcStats.getAverage();
	}

	@Test
	public void testAverageWithArrayOfOneElements() {
		CalcStats calcStats = new CalcStats(new int[] { 3 });
		assertThat(calcStats.getAverage()).isEqualTo(3.0);
	}

	@Test
	public void testAverageWithArrayOfTwoElementsThatCalculatesExactly() {
		CalcStats calcStats = new CalcStats(new int[] { 0, 100 });
		assertThat(calcStats.getAverage()).isEqualTo(50.0);
	}

	@Test
	public void testAverageWithArrayOfTwoElementsWithOneNegative() {
		CalcStats calcStats = new CalcStats(new int[] { -50, 50 });
		assertThat(calcStats.getAverage()).isEqualTo(0.0);
	}

	@Test
	public void testAverageWithArrayOfTwoElementsWithWithDivisionOfInt() {
		CalcStats calcStats = new CalcStats(new int[] { 0, 51 });
		assertThat(calcStats.getAverage()).isEqualTo(25.5);
	}

	@Test
	public void testAverageWithArrayOfTwoElementsWhereEvenIsDivByOdd() {
		CalcStats calcStats = new CalcStats(new int[] { 5, 6, 9 });
		assertThat(calcStats.getAverage()).isEqualTo(6.66, Offset.offset(.01));
	}

	@Test
	public void testAverageWithArrayOfTwoElementsSixty() {
		int[] bigArray = new int[1000000];
		for (int i = 0; i < bigArray.length; i++) {
			bigArray[i] = Integer.MAX_VALUE;
		}
		CalcStats calcStats = new CalcStats(bigArray);
		assertThat(calcStats.getAverage()).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void testOnlySupportUpTo1Million() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Array size should be less that 1000000");
		new CalcStats(new int[1000001]);
	}
	
	@Test
	public void testAverageWithOverflowSituation() {
		int[] bigArray = new int[1000000];
		for (int i = 0; i < bigArray.length; i++) {
			bigArray[i] = Integer.MAX_VALUE * Integer.MAX_VALUE;
		}
		CalcStats calcStats = new CalcStats(bigArray);
		assertThat(calcStats.getAverage()).isEqualTo(Integer.MAX_VALUE * Integer.MAX_VALUE);
	}
}
