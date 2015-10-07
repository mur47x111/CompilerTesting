package inlining;

import java.lang.invoke.DontInline;

import com.oracle.graal.api.directives.GraalDirectives;

import ch.usi.dag.testing.BaseTestCase;
import inlining.target.Complicated;
import inlining.target.Simple;
import inlining.target.Super;

public class PolyMethod1 extends BaseTestCase implements Constants {

	@DontInline
	@Override
	public void target() {
		GraalDirectives.instrumentationBegin(HERE);
		if (GraalDirectives.inCompiledCode())
			isCompiled = true;
		GraalDirectives.instrumentationEnd();
		
		Super o = likely(LIKELY) ? new Simple() : new Complicated();
		o.calculate(RandomGen.nextInt());

		GraalDirectives.instrumentationBegin(PRED);
		if (GraalDirectives.inCompiledCode())
			counter++;
		GraalDirectives.instrumentationEnd();
	}
	
	@Override
	public double expectedRatio() {
		return INLINE;
	}

}
