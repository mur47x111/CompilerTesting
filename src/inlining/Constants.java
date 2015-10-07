package inlining;


public interface Constants {

	// Distance between the invocation node and the delimitation API
	public static final int HERE = 0;
	public static final int PRED = -1;

	public static final double INLINE = 0;
	public static final double NOT_INLINE = 1;

	public static final double LIKELY = 0.8;
	public static final double UNLIKELY = 0.01;
	public static final double EPSILON = 0.005;
	
}
