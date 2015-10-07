package query;

import static org.junit.Assert.assertEquals;

import java.lang.invoke.DontInline;

import org.junit.Test;

import com.oracle.graal.api.directives.GraalDirectives;

import ch.usi.dag.testing.JITTestCase;

public class IsMethodInlined extends JITTestCase {

	public static final int HERE = 0;
	public static final int ITERATIONS = 10000;

	protected boolean isCompiled = false;
	protected int counter = 0;

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

		empty();
	}
	
	public void empty() {
		GraalDirectives.instrumentationBegin(HERE);
		if (GraalDirectives.isMethodInlined())
			counter++;
		GraalDirectives.instrumentationEnd();
	}

	@Test
	public void test() {
		counter = 0;

		for (int i = 0; i < ITERATIONS; i++)
			target();

		assertEquals(counter, ITERATIONS);
	}

}
