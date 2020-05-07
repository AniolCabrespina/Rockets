package domain;

public class Circuit {

	private float circuitLength;
	private float maximumTime;
	private float currentTime;
	private Rocket rocket;

	public Circuit(float meters, float time, Rocket rocket) {
		this.circuitLength = meters;
		this.maximumTime = time;
		this.rocket = rocket;
		this.currentTime = 0;
	}

	public float getCircuitLength() {
		return circuitLength;
	}

	public float getMaximumTime() {
		return maximumTime;
	}
	
	public float getCurrentTime() {
		return currentTime;
	}
	
	public Rocket getRocket() {
		return rocket;
	}

	public void updateCircuit() {
		System.out.println("Starting competition. Circuit length: " + circuitLength + " Max time: " + maximumTime);
		while (currentTime < maximumTime) {
			updateRocket(2.0f);// el 2.0f l'ha de proporcionar la classe estrategia(Depèn del moviment que li
								// haguem programat).
			if (emptyDeposit()) {
				System.out.println("There is no winner \n Te quedaste sin gasofa.");
				return;
			}
			if (winner()) {
				System.out.println("And the winner is: " +rocket.getName()+ " with a time of " + currentTime + "\n"
						+ "Ha ganao pisha! En el segundo: " + currentTime + " segundo/s.");
				return;
			}
			currentTime++;
		}
		System.out.println("There is no winner \n Te quedaste sin tiempo.");
	}

	public void updateRocket(float acceleration) {
		rocket.updatePropellantsAcceleration(acceleration);
		rocket.calculateRocketAcceleration();
		rocket.updateVelocity(currentTime);
		rocket.updateMeters(currentTime);
		rocket.updateDeposit();
		System.out.println(toString(acceleration));
	}

	public String toString(float acceleration) {
		return "Current Time: " + currentTime + " Acceleration: " + acceleration + " Speed: " + rocket.getVelocity()
				+ " Distance: " + rocket.getMeters() + " Circuit: " + circuitLength + " Fuel: "
				+ rocket.getDeposit().getCurrentFuel() + " / " + rocket.getDeposit().getTotalFuel();
	}

	public boolean winner() {
		return rocket.getMeters() >= circuitLength;
	}

	public boolean emptyDeposit() {
		return rocket.getDeposit().getCurrentFuel() <= 0;
	}

}
