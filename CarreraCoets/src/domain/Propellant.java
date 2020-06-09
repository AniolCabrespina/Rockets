package domain;

import utilities.InvalidParamException;

public class Propellant {

	private float maximumAcceleration;
	private float currentAcceleration;

	public Propellant(float maximumAcceleration) throws InvalidParamException{
		if (maximumAcceleration <= 0) {
			throw new InvalidParamException();
		}
		this.maximumAcceleration = maximumAcceleration;
		this.currentAcceleration = 0.0f;
	}
	
	public Propellant(float maximumAcceleration, float currentAcceleration) throws InvalidParamException{
		if (maximumAcceleration <= 0) {
			throw new InvalidParamException();
		}
		this.maximumAcceleration = maximumAcceleration;
		this.currentAcceleration = currentAcceleration;
	}

	public float getMaximumAcceleration() {
		return maximumAcceleration;
	}

	public float getCurrentAcceleration() {
		return currentAcceleration;
	}

	public void setCurrentAcceleration(float newAcceleration) {
		
		this.currentAcceleration = newAcceleration;
		
		if (this.currentAcceleration > maximumAcceleration) {
			this.currentAcceleration = this.maximumAcceleration;
		}
		else if (this.currentAcceleration < 0.0f) {
			this.currentAcceleration = 0.0f;
		}
		
	}

}
