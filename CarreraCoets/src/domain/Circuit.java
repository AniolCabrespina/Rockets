package domain;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import application.dto.CircuitDTO;
import application.dto.RocketDTO;
import utilities.IObserver;
import utilities.ISubject;
import utilities.InvalidParamException;

public class Circuit implements ISubject {

	private String name;
	private float circuitLength;
	private float maximumTime;
	private float currentTime;
	private boolean hasWinner;
	private String winner;
	private List<Rocket> rocketsList = new LinkedList<Rocket>();
	private List<IObserver> observers = new LinkedList<IObserver>();

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
		this.hasWinner = circuitDTO.getHasWinner();
		this.winner = circuitDTO.getWinner();
		for (RocketDTO rocketDTO : circuitDTO.getRocketsList()) {
			rocketsList.add(new Rocket(rocketDTO));
		}
		this.observers = circuitDTO.getObservers();
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

	public List<IObserver> getObservers() {
		return observers;
	}

	public String getWinner() {
		return winner;
	}

	public void updateAllRockets() throws Exception {
		for (Rocket currentRocket : rocketsList) {
			float acceleration = Strategy.getInstance().move(currentTime);
			currentRocket.updateRocket(acceleration, circuitLength);
		}
	}

	public void checkWinner() {
		Rocket previousWinner = new Rocket();
		previousWinner.setCurrentMeters(0.0f);
		for (Rocket currentRocket : rocketsList) {
			if (currentRocket.getCurrentMeters() >= circuitLength && currentRocket.getCurrentMeters() > previousWinner.getCurrentMeters()) {
				hasWinner = true;
				previousWinner = currentRocket;
				winner = currentRocket.getName();
			}
		}
	}
	
	public boolean anyRocketHasFuel() {
		boolean haveFuel = false;
		for(Rocket rocket : rocketsList) {
			if(rocket.getDeposit().getCurrentFuel() > 0.0f) {
				haveFuel = true;
			}
		}
		return haveFuel;
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
		for (IObserver o : observers) {
			o.updateCircuit(new CircuitDTO(this));
		}
	}

	public void notifyAllObserversCircuitHasNoWinner() throws Exception {
		for (IObserver o : observers) {
			o.circuitHasNoWinner();
		}
	}

	public void startRace() throws Exception {
		while (currentTime <= maximumTime && !hasWinner && anyRocketHasFuel()) {
			TimeUnit.SECONDS.sleep(1);
			updateAllRockets();
			checkWinner();
			notifyAllObserversCircuitUpdate();
			if(!hasWinner) {
				currentTime++;
			}
		}
		if (!hasWinner) {
			notifyAllObserversCircuitHasNoWinner();
		}
	}

	public void addRockets(List<Rocket> rocketsList) {
		this.rocketsList = rocketsList;
	}

}
