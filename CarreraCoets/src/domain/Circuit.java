package domain;

import java.util.LinkedList;
import java.util.List;

public class Circuit {

	private float circuitLength;
	private float maximumTime;
	private float currentTime;
	private Rocket currentRocket;
	private List<Rocket> rocketsList;

	public Circuit(float meters, float time) {
		this.circuitLength = meters;
		this.maximumTime = time;
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

	public String updateRocket(float acceleration) {
		currentRocket.updatePropellantsAcceleration(acceleration);
		currentRocket.calculateRocketAcceleration();
		currentRocket.updateVelocity(currentTime);
		currentRocket.updateMeters(currentTime);
		currentRocket.getDeposit().updateDeposit(currentRocket.getVelocity());
		
		return toString(acceleration);
	}

	public String toString(float acceleration) {
		return "Current Time: " + currentTime + " Acceleration: " + acceleration + " Speed: "
				+ currentRocket.getVelocity() + " Distance: " + currentRocket.getMeters() + " Circuit: " + circuitLength
				+ " Fuel: " + currentRocket.getDeposit().getCurrentFuel() + " / "
				+ currentRocket.getDeposit().getTotalFuel();
	}

	public boolean hasWin() {
		return currentRocket.getMeters() >= circuitLength;
	}

	public boolean isDepositEmpty() {
		return currentRocket.getDeposit().getCurrentFuel() <= 0;
	}

	public void setCurrentRocket(Rocket currentRocket) {
		this.currentRocket = currentRocket;
	}

}
