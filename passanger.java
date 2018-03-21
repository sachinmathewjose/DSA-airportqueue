package airpporque;

public class passanger {
	protected static int count = 1;
	protected int number = count++;
	protected int arrivalTime, serviceStartingTime, serviceTime;
	protected station v_station;
	protected boolean firstclass = true;
	public passanger(int arrivalTime,boolean v_firstclass) {
		this.arrivalTime = arrivalTime;
		v_station = null;
		this.firstclass = v_firstclass;
	}
	public void serviceStarting(int serviceStartingTime, int serviceTime, station a_station) {
		this.serviceStartingTime = serviceStartingTime;
		this.serviceTime = serviceTime;
		this.v_station = a_station;
	}
	public int waitTime() {
		return (v_station == null ? 0 : serviceStartingTime - arrivalTime);
	}

}
