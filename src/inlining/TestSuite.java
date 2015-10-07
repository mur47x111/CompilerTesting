package inlining;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SimpleMethod.class, SimpleMethodInSlowPath.class,
		AnnotatedMethod.class, ComplicatedMethod.class,
		ComplicatedMethodInSlowPath.class, PolyMethod1.class,
		PolyMethod2.class, PolyMethod3.class, PolyMethod4.class })
public class TestSuite {
}
