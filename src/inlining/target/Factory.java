package inlining.target;

import java.lang.invoke.DontInline;

import inlining.RandomGen;

public class Factory {

	private static Simple1 s1 = new Simple1();
	private static Simple2 s2 = new Simple2();
	private static Simple3 s3 = new Simple3();
	private static Simple4 s4 = new Simple4();
	private static Simple5 s5 = new Simple5();
	private static Simple6 s6 = new Simple6();
	private static Simple7 s7 = new Simple7();
	private static Simple8 s8 = new Simple8();
	private static Simple9 s9 = new Simple9();
	private static Simple10 s10 = new Simple10();

	/**
	 * @return Super instance of 10 types, each type being 10%.
	 */
	@DontInline
	public static Super nextSuper() {
		double d = RandomGen.nextDouble();

		if (d < 0.1)
			return s1;
		else if (d < 0.2)
			return s2;
		else if (d < 0.3)
			return s3;
		else if (d < 0.4)
			return s4;
		else if (d < 0.5)
			return s5;
		else if (d < 0.6)
			return s6;
		else if (d < 0.7)
			return s7;
		else if (d < 0.8)
			return s8;
		else if (d < 0.9)
			return s9;
		else
			return s10;
	}

	public static class Simple1 extends Super {

		@Override
		public int calculate(int input) {
			return input;
		}

	}

	public static class Simple2 extends Super {

		@Override
		public int calculate(int input) {
			return input;
		}

	}

	public static class Simple3 extends Super {

		@Override
		public int calculate(int input) {
			return input;
		}

	}

	public static class Simple4 extends Super {

		@Override
		public int calculate(int input) {
			return input;
		}

	}

	public static class Simple5 extends Super {

		@Override
		public int calculate(int input) {
			return input;
		}

	}

	public static class Simple6 extends Super {

		@Override
		public int calculate(int input) {
			return input;
		}

	}

	public static class Simple7 extends Super {

		@Override
		public int calculate(int input) {
			return input;
		}

	}

	public static class Simple8 extends Super {

		@Override
		public int calculate(int input) {
			return input;
		}

	}

	public static class Simple9 extends Super {

		@Override
		public int calculate(int input) {
			return input;
		}

	}

	public static class Simple10 extends Super {

		@Override
		public int calculate(int input) {
			return input;
		}

	}

}
