package query;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ IsMethodInlined.class, RootName.class, AllocationType.class,
		LockType.class, LockType2.class })
public class TestSuite {
}
