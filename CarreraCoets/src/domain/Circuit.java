package domain;

import java.util.LinkedList;
import java.util.List;

public class Circuit {

	private float circuitLength;
	private float maximumTime;
	private float currentTime = 0;
	private List<Rocket> rocketsList = new LinkedList<Rocket>();

	public Circuit(float circuitLength, float maximumTime) throws Exception {
		if (circuitLength <= 0) {
			throw new Exception("The Circuit Length can't be less or equal than 0.");
		}
		if (maximumTime <= 0) {
			throw new Exception("The Maximum Time can't be less or equal than 0.");
		}
		this.circuitLength = circuitLength;
		this.maximumTime = maximumTime;
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
		String circuitInfo = "";
		for (Rocket currentRocket : rocketsList) {
			float acceleration = Strategy.getInstance().move(getCurrentTime());
			circuitInfo = updateRocket(acceleration, currentRocket) + "\n";
		}
		return circuitInfo;
	}

	public String updateRocket(float acceleration, Rocket currentRocket) {

		currentRocket.updatePropellantsAcceleration(acceleration);
		currentRocket.calculateRocketAcceleration();
		currentRocket.updateVelocity();
		try {
			currentRocket.updateDeposit(currentRocket.getVelocity());
		} catch (Exception e) {
			return toStringDepositEmpty(currentRocket.calculateRocketAcceleration(), currentRocket);
		}
		currentRocket.updateMeters();

		return toString(currentRocket.calculateRocketAcceleration(), currentRocket);
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

	/**
	 * This function searches for a rocket that has win the race, that means that has arrived to the goal
	 * If no rocket is found, cause no rocket has won
	 * It will return a null
	 * @return
	 */
	public Rocket getWinner() {
		for (Rocket currentRocket : rocketsList) {
			if (currentRocket.getMeters() >= circuitLength) {
				return currentRocket;
			}
		}
		return null;
	}
	
	public boolean timeLeft() {
		return currentTime <= maximumTime;
	}
	
	public void updateTime() {
		currentTime++;
	}

	public boolean isDepositEmpty(Rocket currentRocket) {
		return currentRocket.isDepositEmpty();
	}

}
