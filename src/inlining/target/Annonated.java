package inlining.target;

import java.lang.invoke.DontInline;

public class Annonated extends Super {

	@DontInline
	@Override
	public int calculate(int input) {
		return input;
	}

}
