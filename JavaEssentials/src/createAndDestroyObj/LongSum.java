package createAndDestroyObj;

public class LongSum {

	public static void main(String Args[]) {
		
		//Better that use Long l (Boxed primitive)
		long sum = 0L;
		long start = System.nanoTime();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(duration);
		System.out.println(sum);
		
	}
}
