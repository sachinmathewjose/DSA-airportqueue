package airpporque;

import java.util.Random;

public class randomnum {
	public static int avgRand(int n) {
		Random rand = new Random();
		int a = rand.nextInt(2*n -1) + 1;
		return a;
	}
	public static boolean randArrival(int n) {
		Random rand = new Random();
		return rand.nextDouble() < (1 / (double) n);
		}
}
