package domain;

import java.util.LinkedList;
import java.util.List;

import application.dto.RocketDTO;
import utilities.InvalidParamException;

public class Rocket {

	private String name;
	private float currentVelocity;
	private float currentMeters;
	private Deposit deposit;
	private List<Propellant> propellants;

	public Rocket() {

	}

	public Rocket(String name, Deposit deposit, List<Propellant> propellants) throws InvalidParamException {
		if (name.equals(null) || name.equals("")) {
			throw new InvalidParamException();
		}
		if (deposit.equals(null)) {
			throw new InvalidParamException();
		}
		this.name = name;
		this.currentVelocity = 0.0f;
		this.currentMeters = 0.0f;
		this.deposit = deposit;
		this.propellants = new LinkedList<Propellant>();
		this.propellants.addAll(propellants);
	}

	public Rocket(RocketDTO rocketDTO) throws InvalidParamException {
		if (rocketDTO == null) {
			throw new InvalidParamException();
		}
		this.name = rocketDTO.getName();
		this.currentVelocity = rocketDTO.getCurrentVelocity();
		this.currentMeters = rocketDTO.getCurrentMeters();
		this.deposit = new Deposit(rocketDTO.getDepositTotalFuel(), rocketDTO.getDepositCurrentFuel());
		this.propellants = new LinkedList<Propellant>();
		for (float maximumAcceleration : rocketDTO.getPropellantsMaximumAcceleration()) {
			this.propellants.add(new Propellant(maximumAcceleration, rocketDTO.getCurrentAcceleration()));
		}
	}

	public String getName() {
		return name;
	}

	public Deposit getDeposit() {
		return deposit;
	}

	public float getCurrentVelocity() {
		return currentVelocity;
	}

	public float getCurrentMeters() {
		return currentMeters;
	}
	
	public void setCurrentMeters(float currentMeters) {
		this.currentMeters = currentMeters;
	}
	

	public float calculateRocketAcceleration() {
		float newAcceleration = 0.0f;
		for (Propellant propellant : propellants) {
			newAcceleration += propellant.getCurrentAcceleration();
		}
		return newAcceleration;
	}

	public void updatePropellantsAcceleration(float newAcceleration) {
		for (Propellant propellant : propellants) {
			propellant.setCurrentAcceleration(newAcceleration);
		}
	}

	public void updateVelocity() {
		this.currentVelocity = this.currentVelocity + calculateRocketAcceleration() * 1;
	}

	public void updateMeters() {
		this.currentMeters = (float) (this.currentMeters + (this.currentVelocity * 1)
				+ ((calculateRocketAcceleration() / 2) * Math.pow(1, 2)));
	}

	public void updateDeposit(float currentVelocity) throws Exception {
		deposit.updateDeposit(currentVelocity);
	}

	public boolean isDepositEmpty() {
		return deposit.isEmpty();
	}

	public List<Propellant> getPropellants() {
		return this.propellants;
	}
	
	public void updateRocket(float acceleration, float circuitLength) throws Exception {
		if(deposit.getCurrentFuel() > 0.0f) {
			updatePropellantsAcceleration(acceleration);
			calculateRocketAcceleration();
			updateVelocity();
			updateDeposit(currentVelocity);
			if (deposit.getCurrentFuel() > 0.0f) {
				updateMeters();
			}
			else {
				currentVelocity = 0.0f;
			}
		}		
	}
}
