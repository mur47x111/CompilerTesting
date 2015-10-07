package pea;

import java.lang.invoke.DontInline;

import com.oracle.graal.api.directives.GraalDirectives;

import ch.usi.dag.testing.BaseTestCase;
import pea.target.A;

public class PartialEscape extends BaseTestCase implements Constants {

	public static final double LIKELY = 0.8;
	public static final double EPSILON = 0.02;

	@DontInline
	@Override
	public void target() {
		GraalDirectives.instrumentationBegin(HERE);
		if (GraalDirectives.inCompiledCode())
			isCompiled = true;
		GraalDirectives.instrumentationEnd();

		A a = new A();

		GraalDirectives.instrumentationBegin(PRED);
		if (GraalDirectives.inCompiledCode())
			counter++;
		GraalDirectives.instrumentationEnd();

		if (likely(LIKELY))
			// This will not be inlined and let the receiver escape
			a.notInlinedMethod();
	}

	@Override
	public double expectedRatio() {
		return ESCAPE * LIKELY;
	}

}
