package pea.target;

import java.lang.invoke.DontInline;

public class B {

	public final A a;

	public B(A a) {
		this.a = a;
	}

	public void empty() {
	}

	@DontInline
	public void notInlinedMethod() {
	}

}
