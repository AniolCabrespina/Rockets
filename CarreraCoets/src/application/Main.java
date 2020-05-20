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

	public static void startRace(Circuit circuit) throws Exception {
		System.out.println("Starting competition. Circuit length: " + circuit.getCircuitLength() + " Max time: "
				+ circuit.getMaximumTime());
		updateCircuit(circuit);
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

	public static void updateCircuit(Circuit circuit) throws Exception {
		Rocket winner;
		while (circuit.timeLeft()) {
			System.out.print(circuit.updateAllRockets());
			winner = circuit.getWinner();
			if (winner instanceof Rocket) {
				System.out.println(winnerMessage(winner, circuit));
				return;
			} else {
				circuit.updateTime();
			}
		}
		System.out.println("There is no winner.");
	}

	public static String winnerMessage(Rocket winner, Circuit circuit) {
		String msg = "";
		msg = "And the winner is: " + winner.getName() + " with a time of " + circuit.getCurrentTime() + "\n"
				+ "Ha ganao pisha! En el segundo: " + circuit.getCurrentTime() + " segundo/s.";
		return msg;
	}

}
