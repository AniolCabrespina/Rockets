package domain;

public class Circuit {

	private float totalMeters;
	private float totalTime;
	private Rocket rocket;

	public Circuit(float meters, float time, Rocket rocket) {
		this.totalMeters = meters;
		this.totalTime = time;
		this.rocket = rocket;
	}

	public float getTotalMeters() {
		return totalMeters;
	}

	public float getTotalTime() {
		return totalTime;
	}

	public void updateCircuit() {

		for (float i = 0; i < totalTime; i++) {
			if (emptyDeposit()) {
				System.out.println("Te quedaste sin gasofa.");
				return;
			} else {
				updateRocket(i, 5.0f);// el 5 ha de preguntar a classe estrategia el moviment
				if (winner()) {
					System.out.println("Ha ganao pisha! En el segundo: " + i + " segundo/s.");
					return;
				}
			}
		}
	}

	public void updateRocket(float currentTime, float acceleration) {
		rocket.updatePropellantsAcceleration(acceleration);
		rocket.calculateRocketAcceleration();
		rocket.updateVelocity(currentTime);
		rocket.updateMeters(currentTime);
		rocket.updateDeposit();
	}

	public boolean winner() {
		return rocket.getMeters() >= totalMeters;
	}

	public boolean emptyDeposit() {
		return rocket.getDeposit().getCurrentFuel() <= 0;
	}

}
