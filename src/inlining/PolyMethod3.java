package inlining;

import java.lang.invoke.DontInline;

import com.oracle.graal.api.directives.GraalDirectives;

import ch.usi.dag.testing.BaseTestCase;
import inlining.target.Simple;
import inlining.target.Super;

public class PolyMethod3 extends BaseTestCase implements Constants {

	@DontInline
	@Override
	public void target() {
		GraalDirectives.instrumentationBegin(HERE);
		if (GraalDirectives.inCompiledCode())
			isCompiled = true;
		GraalDirectives.instrumentationEnd();

		Super o = new Simple();

		if (likely(UNLIKELY)) {
			o.calculate(RandomGen.nextInt());

			GraalDirectives.instrumentationBegin(PRED);
			if (GraalDirectives.inCompiledCode())
				counter++;
			GraalDirectives.instrumentationEnd();
		}
	}
	
	@Override
	public double expectedRatio() {
		return INLINE;
	}

}
