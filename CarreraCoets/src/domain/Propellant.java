package domain;

public class Propellant {

	private float maximumAcceleration;
	private float currentAcceleration = 0.0f;

	public Propellant(float maximumAcceleration) throws Exception {
		if (maximumAcceleration <= 0) {
			throw new Exception("Maximum Acceleration can't be less or equal than 0.");
		}
		this.maximumAcceleration = maximumAcceleration;
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
