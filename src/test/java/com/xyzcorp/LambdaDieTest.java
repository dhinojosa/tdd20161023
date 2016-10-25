package com.xyzcorp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.Test;

public class LambdaDieTest {

	@Test
	public void testSimpleRoll4() throws Exception {
		LambdaDie die = new LambdaDie(() -> 4);
		Die copy = die.roll();
		assertThat(copy.getPips()).isEqualTo(4);
	}

	@Test
	public void testIntegrationRealRandom() throws Exception {
		Random random = new Random();
		LambdaDie die = new LambdaDie(() -> random.nextInt(6) + 1);
		Die copy = die.roll();
		assertThat(copy.getPips()).isEqualTo(4);
	}
}
