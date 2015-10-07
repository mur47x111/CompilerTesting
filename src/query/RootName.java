package query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.invoke.DontInline;
import java.util.HashMap;

import org.junit.Test;

import com.oracle.graal.api.directives.GraalDirectives;

import ch.usi.dag.testing.JITTestCase;

public class RootName extends JITTestCase {

	public static final int HERE = 0;
	public static final int ITERATIONS = 10000;

	protected boolean isCompiled = false;
	protected HashMap<String, Integer> counters = new HashMap<>();

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

		GraalDirectives.instrumentationBegin(HERE);
		if (GraalDirectives.inCompiledCode())
			profile(GraalDirectives.rootName());
		GraalDirectives.instrumentationEnd();
	}

	public void empty() {
		GraalDirectives.instrumentationBegin(HERE);
		if (GraalDirectives.inCompiledCode())
			profile(GraalDirectives.rootName());
		GraalDirectives.instrumentationEnd();
	}

	@DontInline
	private void profile(String name) {
		counters.compute(name, (k, v) -> v == null ? 1 : v + 1);
	}

	@Test
	public void test() {
		counters.clear();

		for (int i = 0; i < ITERATIONS; i++)
			target();

		 assertEquals(counters.keySet().size(), 1);
		 assertTrue(counters.keySet().contains("query.RootName.target()V"));
	}

}
