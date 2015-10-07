package pea;

import java.lang.invoke.DontInline;

import com.oracle.graal.api.directives.GraalDirectives;

import ch.usi.dag.testing.BaseTestCase;
import pea.target.A;

public class NotEscape extends BaseTestCase implements Constants {

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
		// This gets inlined and the receiver will not escape
		a.empty();
	}

	@Override
	public double expectedRatio() {
		return NOT_ESCAPE;
	}

}
