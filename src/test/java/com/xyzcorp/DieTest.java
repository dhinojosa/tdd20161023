package com.xyzcorp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;

public class DieTest {

    @Test
    public void testDefaultIs1() {
        Die die = new Die(createMock(Random.class));
        assertThat(die.getPips()).isEqualTo(1);
    }

    @Test
    public void testSimpleRollOf4() {
        //Stub!
        @SuppressWarnings("serial")
		Random random = new Random() {
            public int nextInt(int bound) {
              return 3;
            }
        };

        Die die = new Die(random);
        Die copy = die.roll();
        assertThat(copy.getPips()).isEqualTo(4);
    }

    @Test
    public void testSimpleRollOf2() {
        //Stub!
        Random random = new Random() {
            public int nextInt(int bound) {
                return 1;
            }
        };

        Die die = new Die(random);
        Die copy = die.roll();
        assertThat(copy.getPips()).isEqualTo(2);
    }

    @Test
    public void testTwoRollWithA5And3() {
        //Mock!
        Random random = createMock(Random.class);

        //Rehearse
        expect(random.nextInt(6)).andReturn(4).once().andReturn(2).once();

        //Replay (Rewind) (EasyMock Only)
        replay(random);

        //Test
        Die die = new Die(random);
        Die copy = die.roll().roll();
        assertThat(copy.getPips()).isEqualTo(3);

        //Verify
        verify(random);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testThatRandomIsNotNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Random cannot be null");
        new Die(null);
    }

    @Test
    @Category(value=IntegrationTest.class)
    public void testWithARealRandom() {
        Random random = new Random();
        Die die = new Die(random);
        for (int i = 0; i < 1000000; i++) {
            assertThat(die.roll().getPips()).isGreaterThan(0).isLessThan(7);
        }
    }

    @Test
    @Category(value=UnitTest.class)
    public void testBUG20012() {
        //Mock!
        Random random = createMock(Random.class);

        //Rehearse
        expect(random.nextInt(6)).andReturn(3);

        //Replay (Rewind) (EasyMock Only)
        replay(random);

        //Test
        Die die = new Die(random);
        Die copy = die.roll();
        assertThat(copy.getPips()).isGreaterThan(0).isLessThan(7);

        //Verify
        verify(random);
    }
}


