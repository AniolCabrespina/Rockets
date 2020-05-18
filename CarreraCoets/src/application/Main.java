package application;

import java.util.LinkedList;
import java.util.List;

import domain.Circuit;
import domain.Propellant;
import domain.Deposit;
import domain.Rocket;

public class Main {

	public static void main(String[] args) {

		try {
			Rocket rocket = createRocket();
			Circuit circuit = new Circuit(1200.0f, 18.0f);
			circuit.addRocket(rocket);
			startRace(circuit);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void updateCircuit(Circuit circuit) throws Exception {
		Rocket winner;
		while (timeLeft(circuit)) {
			System.out.print(circuit.updateAllRockets());
			winner = circuit.hasWin();
			if (winner instanceof Rocket) {
				System.out.println(
						"And the winner is: " + winner.getName() + " with a time of " + circuit.getCurrentTime() + "\n"
								+ "Ha ganao pisha! En el segundo: " + circuit.getCurrentTime() + " segundo/s.");
				return;
			}
			updateTime(circuit);
		}
		System.out.println("There is no winner.");
	}

	public static Rocket createRocket() throws Exception {
		List<Propellant> rocketPropellants = new LinkedList<Propellant>();
		rocketPropellants.add(new Propellant(50.0f));
		rocketPropellants.add(new Propellant(20.0f));
		rocketPropellants.add(new Propellant(38.0f));

		Deposit fuelTank = new Deposit(2500.0f);
		Rocket rocket = new Rocket("ViperX", fuelTank);
		rocket.addRocketPropellants(rocketPropellants);

		return rocket;
	}

	public static void startRace(Circuit circuit) throws Exception {
		System.out.println("Starting competition. Circuit length: " + circuit.getCircuitLength() + " Max time: "
				+ circuit.getMaximumTime());
		updateCircuit(circuit);
	}
	
	public static boolean timeLeft(Circuit circuit) {
		return circuit.getCurrentTime() <= circuit.getMaximumTime();
	}
	
	public static void updateTime(Circuit circuit) {
		circuit.setCurrentTime(circuit.getCurrentTime() + 1);
	}
}
