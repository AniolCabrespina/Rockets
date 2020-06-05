package domain;

import java.util.LinkedList;
import java.util.List;

import application.dto.RocketDTO;
import utilities.InvalidParamException;

public class Rocket {

	private String name;
	private List<Propellant> propellants = new LinkedList<Propellant>();
	private Deposit deposit;
	private float currentVelocity = 0.0f;
	private float currentMeters = 0.0f;
 
	public Rocket(String name, Deposit deposit) throws InvalidParamException {
		if(name.equals(null) || name.equals("")) {
			throw new InvalidParamException();
		}
		if(deposit.equals(null)) {
			throw new InvalidParamException();
		}
		this.name = name;
		this.deposit = deposit;
	}

	public Rocket(RocketDTO rocketDTO) {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public float getVelocity() {
		return currentVelocity;
	}

	public float getMeters() {
		return currentMeters;
	}

	public Deposit getDeposit() {
		return deposit;
	}

	public float calculateRocketAcceleration() {
		float newAcceleration = 0.0f;
		for (Propellant propellant: propellants) {
			newAcceleration += propellant.getCurrentAcceleration();
		}
		return newAcceleration;
	}
	
	public void updatePropellantsAcceleration(float newAcceleration) {
		for (Propellant propellant: propellants) {
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
	
	public void addRocketPropellants(List<Propellant> propellants) {
		for (Propellant propellant: propellants) {
			this.propellants.add(propellant);
		}
	}
	
	public boolean isDepositEmpty() {
		return deposit.isEmpty();
	}

}
