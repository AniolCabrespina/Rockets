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
		currentFuel -= consumption;
	}
}
