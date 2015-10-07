package query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.invoke.DontInline;

import org.junit.Test;

import com.oracle.graal.api.directives.GraalDirectives;

import ch.usi.dag.testing.JITTestCase;
import pea.target.A;

public class AllocationType extends JITTestCase {

	public static final int PRED = -2;
	public static final int HERE = 0;
	public static final double LIKELY = 0.8;
	public static final int ITERATIONS = 10000;

	protected boolean isCompiled = false;
	protected int[] counters = new int[3];

	@Override
	protected void warmup() {
		target();
	}

	@Override
	protected boolean isWarmedUp() {
		return isCompiled;
	}

	@DontInline
	public void target() {
		GraalDirectives.instrumentationBegin(HERE);
		if (GraalDirectives.inCompiledCode())
			isCompiled = true;
		GraalDirectives.instrumentationEnd();

		A a = new A();

		GraalDirectives.instrumentationBegin(PRED);
		if (GraalDirectives.inCompiledCode())
			profile(GraalDirectives.runtimePath());
		GraalDirectives.instrumentationEnd();

		if (likely(LIKELY))
			// This will not be inlined and let the receiver escape
			a.notInlinedMethod();
	}

	@DontInline
	private void profile(int allocationType) {
		counters[allocationType + 1]++;
	}

	@Test
	public void test() {
		for (int i = 0; i < counters.length; i++)
			counters[i] = 0;

		for (int i = 0; i < ITERATIONS; i++)
			target();

		assertEquals(counters[0], 0);
		assertTrue(counters[1] + counters[2] > 0);
	}

}
