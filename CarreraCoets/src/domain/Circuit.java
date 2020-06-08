package domain;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import application.dto.CircuitDTO;
import utilities.IObserver;
import utilities.ISubject;
import utilities.InvalidParamException;

public class Circuit implements ISubject{

	private String name;
	private float circuitLength;
	private float maximumTime;
	private float currentTime;
	private List<Rocket> rocketsList = new LinkedList<Rocket>();
	private List<IObserver> observers = new LinkedList<IObserver>();
	private boolean hasWinner;

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
		this.hasWinner = false;
	}

	public Circuit(CircuitDTO circuitDTO) throws InvalidParamException {
		if (circuitDTO == null) {
			throw new InvalidParamException();
		}
		this.name = circuitDTO.getName();
		this.circuitLength = circuitDTO.getCircuitLength();
		this.maximumTime = circuitDTO.getMaximumTime();
		this.currentTime = 0.0f;
		this.hasWinner = false;
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

	public float getCurrentTime() {
		return currentTime;
	}

	public boolean getHasWinner() {
		return hasWinner;
	}

	public List<Rocket> getRocketsList() {
		return rocketsList;
	}

	public String updateAllRockets() throws InvalidParamException {
		String circuitInfo = "";
		for (Rocket currentRocket : rocketsList) {
			float acceleration = Strategy.getInstance().move(currentTime);
			circuitInfo += currentRocket.updateRocket(acceleration,circuitLength) + "\n";
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
				hasWinner = true;
				return currentRocket;
			}
		}
		return null;
	}

	public boolean isDepositEmpty(Rocket currentRocket) {
		return currentRocket.isDepositEmpty();
	}	
	
	public void addObserver(IObserver o) {
		observers.add(o);
	}
	
	public void removeObserver(IObserver o) {
		observers.remove(o);
	}
	
	public void notifyAllObserversCircuitUpdate() throws Exception {
		for(IObserver o : observers) {
			o.updateCircuit();
		}
	}
	
	public void notifyAllObserversCircuitHasNoWinner() throws Exception {
		for(IObserver o : observers) {
			o.circuitHasNoWinner();
		}
	}
	
	public void startRace() throws Exception {
		while (currentTime <= maximumTime && !hasWinner) {
			TimeUnit.SECONDS.sleep(1);
			notifyAllObserversCircuitUpdate();
			currentTime++;
		}
		if(!hasWinner) {
			notifyAllObserversCircuitHasNoWinner();
		}		
	}

	public void addRockets(List<Rocket> rocketsList) {
		this.rocketsList = rocketsList;
		
	}
	
	
}
