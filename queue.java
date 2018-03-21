package airpporque;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class queue {
	//delcaration of variables
	public int checkin, avgArrivalCoach, avgArrivalFirst, avgServiceCoach, avgServiceFirst;
	public int totalServiceTimeCoach = 0, totalServiceTimeFirstClass = 0;
	public int totalPassengersCoach = 0, totalPassengersFirstClass = 0, totalCoachPassengersAtFirstClass = 0;
	public int maxServiceTimeCoach = 0, maxServiceTimeFirstClass = 0;
	public int totalWaitTimeCoach = 0, totalWaitTimeFirstClass = 0;
	int maxQLengthFirstClass = 0, maxQLengthCoach = 0;
	List<station> serviceStations;
	queue(int a_checkin, int a_avgarrivalcoach, int a_avgArrivalFirst,
			int a_avgServiceCoach, int a_avgServiceFirst ) {
		checkin = a_checkin;
		avgArrivalCoach = a_avgarrivalcoach;
		avgArrivalFirst = a_avgArrivalFirst;
		avgServiceCoach = a_avgServiceCoach;
		avgServiceFirst = a_avgServiceFirst;
		startqueue();
	}
	void startqueue() {
		
		clasqueue firstq = new clasqueue();
		clasqueue coachq = new clasqueue();
		
		List<passanger> fullPassengers = new ArrayList<passanger>();
		serviceStations = new ArrayList<station>();
		
		passanger.count = 1;
		
		serviceStations.add(new station(1)); //
		serviceStations.add(new station(2));
		serviceStations.add(new station(3));
		serviceStations.add(new station(4));
		serviceStations.add(new station(5));
		
		int duration = 0;		
		while (duration < checkin) {
			duration++;
			if(duration < (checkin-40)) {
				if (randomnum.randArrival(avgArrivalFirst)) {
					passanger p = new passanger(duration,true);
					fullPassengers.add(p);
					firstq.enQueue(p);
				}
				if (randomnum.randArrival(avgArrivalCoach)) {
					passanger p = new passanger(duration,false);
					fullPassengers.add(p);
					coachq.enQueue(p);
				}
			}
			while (!firstq.isEmptyQueue()) {
				station s = getFreeStation(true);
				if (s == null)
					break;
				passanger p = (passanger) firstq.deQueue();
				int serviceTime = randomnum.avgRand(avgServiceFirst);
				p.serviceStarting(duration, serviceTime, s);
				s.servicePassenger(p, serviceTime);
			}
			while (!coachq.isEmptyQueue()) {
				station s = getFreeStation(false);
				if (s == null)
					break;
				passanger p = (passanger) coachq.deQueue();
				int serviceTime = randomnum.avgRand(avgServiceCoach);
				p.serviceStarting(duration, serviceTime, s);
				s.servicePassenger(p, serviceTime);
			}
			for (station s : serviceStations)
				s.clockIncrement();
			if (firstq.queueSize() > maxQLengthFirstClass)
				maxQLengthFirstClass = firstq.queueSize();
			if (coachq.queueSize() > maxQLengthCoach)
				maxQLengthCoach = coachq.queueSize();
		}
		for (passanger p : fullPassengers) {
			if (p.firstclass)
				totalWaitTimeFirstClass += p.waitTime();
			else {
				totalWaitTimeCoach += p.waitTime();
				if (p.v_station != null && p.v_station.number < 3)
					totalCoachPassengersAtFirstClass++;
			}
		}
		for (station s : serviceStations) {
			if (s.number == 1 || s.number == 2) {
				totalServiceTimeFirstClass += s.totalServiceTime;
				totalPassengersFirstClass += s.passengersServiced;
				if (s.maxServiceTime > maxServiceTimeFirstClass)
					maxServiceTimeFirstClass = s.maxServiceTime;
			} else {
				totalServiceTimeCoach += s.totalServiceTime;
				totalPassengersCoach += s.passengersServiced;
				if (s.maxServiceTime > maxServiceTimeCoach)
					maxServiceTimeCoach = s.maxServiceTime;
			}
		}
		printresult();		
	}
	
	private void printresult() {
		System.out.printf("Average First Class Service Time: %f\n",
				(double) totalServiceTimeFirstClass	/ totalPassengersFirstClass);
		System.out.printf("Average Coach Service Time:  %f\n",
				(double) totalServiceTimeCoach
								/ totalPassengersCoach);
		System.out.printf("Max First Class Service Time:  %f\n",
				(double)maxServiceTimeFirstClass);
		System.out.printf("Max Coach Service Time:  %f\n",(double)maxServiceTimeCoach);
		System.out.printf("Total First Class Passengers Served:  %f\n",
				(double)totalPassengersFirstClass);
		System.out.printf("Total Coach Passengers Served:  %f\n",
				(double)totalPassengersCoach);
		System.out.printf("Total Coach Passengers Served at First Class:  %f\n",
				(double)totalCoachPassengersAtFirstClass);
		System.out.printf("Max First Class Queue Length:  %f\n",
				(double)maxQLengthFirstClass);
		System.out.printf("Max Coach Queue Length:  %f\n", (double)maxQLengthCoach);
		System.out.printf("Average First Class Wait Time:  %f\n",
				((double) totalWaitTimeFirstClass/ totalPassengersFirstClass));
		System.out.printf("Average Coach Wait Time:  %f\n",
				(double) totalWaitTimeCoach	/ totalPassengersFirstClass);
	}
	
	private station getFreeStation(boolean firstClassOnly) {
		if (firstClassOnly) {
			for(int i=2; i<5; i++) {
				if (serviceStations.get(i).isAvailable())
					return serviceStations.get(i);
			}
		}
		for (int i=0; i<2; i++) {
			if (serviceStations.get(i).isAvailable())
				return serviceStations.get(i);
		}
		return null;
	}
}
