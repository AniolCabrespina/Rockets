package domain;

public class Propellant {

	private float maximumAcceleration;
	private float currentAcceleration;

	public Propellant(float maximumAcceleration) {
		this.maximumAcceleration = maximumAcceleration;
		this.currentAcceleration = 0.0f;
	}

	public float getMaximumAcceleration() {
		return maximumAcceleration;
	}

	public float getCurrentAcceleration() {
		return currentAcceleration;
	}

	public void setCurrentAcceleration(float newAcceleration) {
		if (newAcceleration > maximumAcceleration) {
			this.currentAcceleration = this.maximumAcceleration;
		}
		if (newAcceleration < 0.0f) {
			this.currentAcceleration = 0.0f;
		}
		this.currentAcceleration += newAcceleration;
	}

}
