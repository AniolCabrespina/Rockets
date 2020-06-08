package domain;

import java.util.List;
import java.util.concurrent.TimeUnit;

import application.dto.CircuitDTO;
import utilities.InvalidParamException;

public class Circuit {

	private String name;
	private float circuitLength;
	private float maximumTime;
	private float currentTime;
	private boolean hasEnded;
	private List<Rocket> rocketsList;

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
		this.currentTime = 0.0f;
	}

	public Circuit(CircuitDTO circuitDTO) throws InvalidParamException {
		if (circuitDTO == null) {
			throw new InvalidParamException();
		}
		this.name = circuitDTO.getName();
		this.circuitLength = circuitDTO.getCircuitLength();
		this.maximumTime = circuitDTO.getMaximumTime();
		this.currentTime = 0.0f;
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

	public String updateAllRockets() throws InvalidParamException {
		String circuitInfo = "";
		for (Rocket currentRocket : rocketsList) {
			float acceleration = Strategy.getInstance().move(currentTime);
			circuitInfo += currentRocket.updateRocket(acceleration, currentTime) + "\n";
		}
		return circuitInfo;
	}

	/**
	 * This function searches for a rocket that has win the race, that means that
	 * has arrived to the goal If no rocket is found, cause no rocket has won It
	 * will return a null
	 * 
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

	public boolean isDepositEmpty(Rocket currentRocket) {
		return currentRocket.isDepositEmpty();
	}
	
	public String updateCricuit() {
		for (int currentTime = 0; currentTime <= circuitDTO.getMaximumTime(); currentTime++) {
			TimeUnit.SECONDS.sleep(1);
			System.out.print(controller.updateCircuit(circuitDTO, currentTime));
			if(controller.getHasEnded()) {
				return null;
			}
		}
		System.out.println("There is no winner.");	
	}
	
	

}
