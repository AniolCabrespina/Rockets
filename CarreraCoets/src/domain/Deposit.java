package domain;

import utilities.InvalidParamException;

public class Deposit {

	private float totalFuel;
	private float currentFuel;

	public Deposit(float totalFuel) throws InvalidParamException {
		if (totalFuel <= 0) {
			throw new InvalidParamException();
		}
		this.totalFuel = totalFuel;
		this.currentFuel = this.totalFuel;
	}
	
	public Deposit(float totalFuel, float currentFuel) throws InvalidParamException {
		if (totalFuel <= 0) {
			throw new InvalidParamException();
		}
		this.totalFuel = totalFuel;
		this.currentFuel = currentFuel;
	}

	public float getTotalFuel() {
		return totalFuel;
	}

	public float getCurrentFuel() {
		return currentFuel;
	}
	
	public void setCurrentFuel(float currentFuel) {
		this.currentFuel = currentFuel;
	}

	public void updateFuel(float consumption) throws InvalidParamException {
		if ((currentFuel - consumption) < 0) {
			currentFuel = 0;
		}
		else{
			currentFuel -= consumption;
		}
	}
	
	public void updateDeposit(float currentVelocity) throws InvalidParamException{
		float consumption = (float) (0.02f * Math.pow(currentVelocity, 2));
		updateFuel(consumption);
	}
	
	public boolean isEmpty() {
		return currentFuel <= 0;
	}
}
