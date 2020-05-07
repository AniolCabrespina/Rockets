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

	public Rocket(String name, Deposit deposit) {
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

	public void updateVelocity(float time) {
		this.currentVelocity = this.currentVelocity + currentAcceleration * time;
	}

	public void updateMeters(float time) {
		this.currentMeters = (float) (this.currentMeters + (this.currentVelocity * time)
				+ ((currentAcceleration / 2) * Math.pow(time, 2)));
	}

	public void updateDeposit() {
		float consumption = (float) (0.02f * Math.pow(currentVelocity, 2));
		deposit.recalculateValue(consumption);
	}

	public void addRocketPropellants(List<Propellant> propellants) {
		for (int i = 0; i < propellants.size(); i++) {
			this.propellants.add(propellants.get(i));
		}
	}

}
