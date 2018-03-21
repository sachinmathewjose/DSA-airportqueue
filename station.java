package airpporque;

public class station {
	protected int number, passengersServiced, totalServiceTime, maxServiceTime;
	protected passanger vPassenger;
	protected int serviceTime;
	public station(int number) {
		this.number = number;
		passengersServiced = 0;
		totalServiceTime = 0;
		maxServiceTime = 0;
		vPassenger = null;
	}
	public boolean isAvailable() {
		return (vPassenger == null);
	}
	public boolean servicePassenger(passanger aPassenger, int aServiceTime) {
		if (vPassenger != null)
			return false;
		
		serviceTime = aServiceTime;
		vPassenger = aPassenger;
		
		if (serviceTime > maxServiceTime)
			maxServiceTime = serviceTime;
		totalServiceTime += serviceTime;
		
		passengersServiced++;
		return true;
	}
	
	public void clockIncrement() {
		if (vPassenger == null)
			return;
		serviceTime--;
		if (serviceTime == 0)
			vPassenger = null;
	}
	
}