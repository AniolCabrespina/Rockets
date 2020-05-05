package domain;

public class Deposit {

	private float totalFuel;
	private float currentFuel;
	private float consumption;
	
	public Deposit(float total) {
		this.totalFuel = total;
		this.currentFuel = this.totalFuel;
	}
	
	public float getTotalValue() {return totalFuel;}
	public float getCurrentValue() {return currentFuel;}
	public float getConsumption() {return consumption;}
	
	public void calculateConsumption(float velocity) {		
		consumption = (float) (0.02f * Math.pow(velocity, 2));
	}
	
	public void recalculateValue() {
		currentFuel -= consumption;
	}
}
