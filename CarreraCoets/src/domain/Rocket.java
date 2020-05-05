package domain;

import java.util.LinkedList;
import java.util.List;

public class Rocket {

	private List<Propellant> propellants;
	private Deposit deposit;
	private float currentAcceleration;
	private float currentVelocity;
	private float currentMeters;

	public Rocket(Deposit deposit) {
		this.deposit = deposit;
		this.propellants = new LinkedList<Propellant>();
		this.currentVelocity = 0.0f;
		this.currentMeters = 0.0f;
	}

	public float getVelocity() {
		return currentVelocity;
	}

	public float getMeters() {
		return currentMeters;
	}

	public void updateAcceleration() {
		for(int i = 0; i < propellants.size(); i++) {
			currentAcceleration += propellants.get(i).getCurrentAcceleration();
		}
	}

	public void updateVelocity(float time) {
		
		this.currentVelocity = this.currentVelocity + currentAcceleration * time; 
		
	}

	public void updateMeters(float time) {
		
		this.currentMeters = (float) (this.currentMeters
				+ (this.currentVelocity * time) + ((currentAcceleration/2) * Math.pow(time,2)));
	}

	public void addRocketPropellants(List<Propellant> propellants) {
		for (int i = 0; i < propellants.size(); i++) {
			this.propellants.add(propellants.get(i));
		}
	}

}
