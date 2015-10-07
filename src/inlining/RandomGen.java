package inlining;

import java.lang.invoke.DontInline;
import java.util.Random;

public class RandomGen {

	public static Random r = new Random();

	@DontInline
	public static int nextInt() {
		return r.nextInt();
	}
	
	@DontInline
	public static double nextDouble() {
		return r.nextDouble();
	}

}
