package airpporque;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		int checkin, avgArrivalCoach, avgArrivalFirst, avgServiceCoach, avgServiceFirst;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the duration of check in(>40): ");
		checkin = in.nextInt();
		System.out.println("Enter the avg arrival time for coch: ");
		avgArrivalCoach = in.nextInt();
		System.out.println("Enter the avg arrival time for first class: ");
		avgArrivalFirst = in.nextInt();
		System.out.println("Enter the maximum service time for coch: ");
		avgServiceCoach = in.nextInt();
		System.out.println("Enter the maximum service time for first class: ");
		avgServiceFirst = in.nextInt();
		queue q = new queue(checkin, avgArrivalCoach, avgArrivalFirst, avgServiceCoach, avgServiceFirst);
	}

}