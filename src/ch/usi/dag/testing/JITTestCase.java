package ch.usi.dag.testing;

import java.lang.invoke.DontInline;
import java.util.Random;

import org.junit.Before;

public abstract class JITTestCase {

	private final Random random = new Random();

	/**
	 * Repeatedly trains target methods till they are compiled.
	 */
	@Before
	public final void warmUpTarget() {
		while (!isWarmedUp()) {
			warmup();
		}
	}

	@DontInline
	protected final boolean likely(double probability) {
		return random.nextDouble() < probability;
	}

	/**
	 * Trains target methods.
	 */
	protected abstract void warmup();

	/**
	 * Determines compilation status of the target methods. This is implemented
	 * using static query isMethodCompiled() in the target methods, which can
	 * guard a callback that changes the compilation status.
	 */
	protected abstract boolean isWarmedUp();

}
