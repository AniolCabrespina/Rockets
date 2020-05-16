package domain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Circuit {

	private float circuitLength;
	private float maximumTime;
	private float currentTime;
	private List<Rocket> rocketsList;

	public Circuit(float circuitLength, float maximumTime) throws Exception {
		if (circuitLength <= 0) {
			throw new Exception("The Circuit Length can't be less or equal than 0.");
		}
		if (maximumTime <= 0) {
			throw new Exception("The Maximum Time can't be less or equal than 0.");
		}
		this.circuitLength = circuitLength;
		this.maximumTime = maximumTime;
		this.currentTime = 0;
		this.rocketsList = new LinkedList<Rocket>();
	}

	public float getCircuitLength() {
		return circuitLength;
	}

	public float getMaximumTime() {
		return maximumTime;
	}

	public float getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(float currentTime) {
		this.currentTime = currentTime;
	}

	public List<Rocket> getRocketsList() {
		return this.rocketsList;
	}

	public void addRocket(Rocket rocket) {
		rocketsList.add(rocket);
	}

	public String updateAllRockets() throws Exception {
		Iterator it = rocketsList.iterator();
		String circuitInfo = "";
		while (it.hasNext()) {
			Rocket currentRocket = (Rocket) it.next();
			circuitInfo = updateRocket(Strategy.getInstance().move(getCurrentTime()),currentRocket) + "\n";
		}

		if (circuitInfo.equals("")) {
			throw new Exception("Error: There are no rockets.");
		}
		return circuitInfo;
	}

	public String updateRocket(float acceleration, Rocket currentRocket) {

		currentRocket.updatePropellantsAcceleration(acceleration);
		currentRocket.calculateRocketAcceleration();
		currentRocket.updateVelocity();
		currentRocket.getDeposit().updateDeposit(currentRocket.getVelocity());

		if (isDepositEmpty(currentRocket)) {
			return toStringDepositEmpty(currentRocket.getAcceleration(), currentRocket);
		}

		currentRocket.updateMeters();

		return toString(currentRocket.getAcceleration(), currentRocket);
	}

	public String toString(float acceleration, Rocket currentRocket) {
		return "Current Time: " + currentTime + " Acceleration: " + acceleration + " Speed: "
				+ currentRocket.getVelocity() + " Distance: " + currentRocket.getMeters() + " Circuit: " + circuitLength
				+ " Fuel: " + currentRocket.getDeposit().getCurrentFuel() + " / "
				+ currentRocket.getDeposit().getTotalFuel();
	}

	public String toStringDepositEmpty(float acceleration, Rocket currentRocket) {
		return "Current Time: " + currentTime + " Acceleration: " + acceleration + " Speed: 0.0 Distance: "
				+ currentRocket.getMeters() + " Circuit: " + circuitLength + " Fuel: "
				+ currentRocket.getDeposit().getCurrentFuel() + " / " + currentRocket.getDeposit().getTotalFuel();
	}

	public Rocket hasWin() {
		Iterator it = rocketsList.iterator();
		while (it.hasNext()) {
			Rocket currentRocket = (Rocket) it.next();
			if(currentRocket.getMeters() >= circuitLength) {
				return currentRocket;
			}
		}
		return null;
	}

	public boolean isDepositEmpty(Rocket currentRocket) {
		return currentRocket.getDeposit().getCurrentFuel() <= 0;
	}

}
