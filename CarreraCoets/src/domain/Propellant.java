package domain;

public class Propellant {
	
	private float maximumAcceleration;
	private float currentAcceleration;
	
	public Propellant (float maximumAcceleration) {
		this.maximumAcceleration = maximumAcceleration;
		this.currentAcceleration = 0.0f;
	}
	
	public float getMaximumAcceleration() {
		return maximumAcceleration;
	}
	
	public float getCurrentAcceleration() {
		return currentAcceleration;
	}
	
	public void increaseCurrentAcceleration(float increase) throws Exception {
		if(currentAcceleration + increase > maximumAcceleration) {
			this.currentAcceleration = this.maximumAcceleration;
		}
		this.currentAcceleration += increase;
	}

}
