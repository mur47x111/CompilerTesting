package inlining.target;

public class Complicated extends Super {

	@Override
	public int calculate(int input) {
		return ((((((input + 1) * 2) - 3) / 4) ^ 5) % 6) & 7;
	}

}
