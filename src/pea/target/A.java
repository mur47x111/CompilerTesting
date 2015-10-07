package pea.target;

import java.lang.invoke.DontInline;

public class A {

	public void empty() {
	}

	@DontInline
	public void notInlinedMethod() {
	}

}
