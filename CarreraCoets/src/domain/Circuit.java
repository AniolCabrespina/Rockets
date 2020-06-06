package domain;

import java.util.List;

import application.dto.CircuitDTO;
import utilities.InvalidParamException;

public class Circuit {

	private String name;
	private float circuitLength;
	private float maximumTime;

	public Circuit() {

	}

	public Circuit(String name, float circuitLength, float maximumTime) throws InvalidParamException {
		if (name == null || name.equals("")) {
			throw new InvalidParamException();
		}
		if (circuitLength <= 0.0f) {
			throw new InvalidParamException();
		}
		if (maximumTime <= 0.0f) {
			throw new InvalidParamException();
		}
		this.name = name;
		this.circuitLength = circuitLength;
		this.maximumTime = maximumTime;
	}

	public Circuit(CircuitDTO circuitDTO) throws InvalidParamException {
		if (circuitDTO == null) {
			throw new InvalidParamException();
		}
		this.name = circuitDTO.getName();
		this.circuitLength = circuitDTO.getCircuitLength();
		this.maximumTime = circuitDTO.getMaximumTime();
	}

	public String getName() {
		return name;
	}

	public float getCircuitLength() {
		return circuitLength;
	}

	public float getMaximumTime() {
		return maximumTime;
	}

	public String updateAllRockets(List<Rocket> rocketsList, float currentTime) throws InvalidParamException {
		String circuitInfo = "";
		for (Rocket currentRocket : rocketsList) {
			float acceleration = Strategy.getInstance().move(currentTime);
			circuitInfo += updateRocket(acceleration, currentRocket) + "\n";
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
		return "\t Rocket: " + currentRocket.getName() + " Acceleration: " + acceleration + " Speed: "
				+ currentRocket.getVelocity() + " Distance: " + currentRocket.getMeters() + "/" + circuitLength
				+ " Fuel: " + currentRocket.getDeposit().getCurrentFuel() + " / "
				+ currentRocket.getDeposit().getTotalFuel();
	}

	public String toStringDepositEmpty(float acceleration, Rocket currentRocket) {
		return "\t Rocket: " + currentRocket.getName() + " Acceleration: " + acceleration + " Speed: 0.0 Distance: "
				+ currentRocket.getMeters() + " Circuit: " + circuitLength + " Fuel: "
				+ currentRocket.getDeposit().getCurrentFuel() + " / " + currentRocket.getDeposit().getTotalFuel();
	}

	/**
	 * This function searches for a rocket that has win the race, that means that
	 * has arrived to the goal If no rocket is found, cause no rocket has won It
	 * will return a null
	 * 
	 * @return
	 */
	public Rocket getWinner(List<Rocket> rocketsList) {
		for (Rocket currentRocket : rocketsList) {
			if (currentRocket.getMeters() >= circuitLength) {
				return currentRocket;
			}
		}
		return null;
	}

	public boolean isDepositEmpty(Rocket currentRocket) {
		return currentRocket.isDepositEmpty();
	}

}
