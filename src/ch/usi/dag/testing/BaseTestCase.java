package ch.usi.dag.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class BaseTestCase extends JITTestCase {

	public static final int ITERATIONS = 10000;
	public static final double DEFAULT_ERROR = 0.02;

	protected boolean isCompiled = false;
	protected int counter = 0;

	@Override
	protected final void warmup() {
		target();
	}

	@Override
	protected final boolean isWarmedUp() {
		return isCompiled;
	}

	@Test
	public final void test() {
		counter = 0;
		for (int i = 0; i < ITERATIONS; i++)
			target();
		verify();
	}

	public final void verify() {
		assertEquals(((double) counter) / ITERATIONS, expectedRatio(),
				DEFAULT_ERROR);
	}

	public abstract void target();

	public abstract double expectedRatio();

}
