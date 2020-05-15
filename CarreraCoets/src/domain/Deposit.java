package domain;

public class Deposit {

	private float totalFuel;
	private float currentFuel;

	public Deposit(float total) {
		this.totalFuel = total;
		this.currentFuel = this.totalFuel;
	}

	public float getTotalFuel() {
		return totalFuel;
	}

	public float getCurrentFuel() {
		return currentFuel;
	}

	public void recalculateValue(float consumption) {
		if ((currentFuel - consumption) < 0) {
			currentFuel = 0;
		}
		else{
			currentFuel -= consumption;
		}
	}
	
	public void updateDeposit(float currentVelocity) {
		float consumption = (float) (0.02f * Math.pow(currentVelocity, 2));
		recalculateValue(consumption);
	}
}
