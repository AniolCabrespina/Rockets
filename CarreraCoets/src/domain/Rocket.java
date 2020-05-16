package domain;

import java.util.LinkedList;
import java.util.List;

public class Rocket {

	private String name;
	private List<Propellant> propellants;
	private Deposit deposit;
	private float currentAcceleration;
	private float currentVelocity;
	private float currentMeters;

	public Rocket(String name, Deposit deposit) throws Exception {
		if(name.equals(null) || name.equals("")) {
			throw new Exception("Name can't be null/empty.");
		}
		if(deposit.equals(null)) {
			throw new Exception("Deposit can't be null.");
		}
		this.name = name;
		this.deposit = deposit;
		this.propellants = new LinkedList<Propellant>();
		this.currentVelocity = 0.0f;
		this.currentMeters = 0.0f;
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

	public float getAcceleration() {
		return currentAcceleration;
	}

	public Deposit getDeposit() {
		return deposit;
	}

	public void calculateRocketAcceleration() {
		float newAcceleration = 0.0f;
		for (int i = 0; i < propellants.size(); i++) {
			newAcceleration += propellants.get(i).getCurrentAcceleration();
		}
		this.currentAcceleration = newAcceleration;
	}

	public void updatePropellantsAcceleration(float newAcceleration) {
		for (int i = 0; i < propellants.size(); i++) {
			propellants.get(i).setCurrentAcceleration(newAcceleration);
		}
	}

	public void updateVelocity() {
		this.currentVelocity = this.currentVelocity + currentAcceleration * 1;
	}

	public void updateMeters() {
		this.currentMeters = (float) (this.currentMeters + (this.currentVelocity * 1)
				+ ((currentAcceleration / 2) * Math.pow(1, 2)));
	}
	
	public void addRocketPropellants(List<Propellant> propellants) {
		for (int i = 0; i < propellants.size(); i++) {
			this.propellants.add(propellants.get(i));
		}
	}

}
