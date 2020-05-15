package application;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import domain.Circuit;
import domain.Propellant;
import domain.Deposit;
import domain.Rocket;

public class Main {

	public static void main(String[] args) {

		Rocket rocket = createRocketViperX();
		Circuit circuit = new Circuit(1200.0f, 18.0f);
		circuit.addRocket(rocket);
		startRace(circuit);

	}

	public static void updateCircuit(Circuit circuit) {
		List<Rocket> rocketsList = circuit.getRocketsList();
		while (circuit.getCurrentTime() < circuit.getMaximumTime()) {
			Iterator it = rocketsList.iterator();
			while (it.hasNext()) {
				Rocket currentRocket = (Rocket) it.next();
				circuit.setCurrentRocket(currentRocket);
				System.out.println(circuit.updateRocket(2.0f));// el 2.0f l'ha de proporcionar la classe
																// estrategia(Depèn del moviment que li haguem
																// programat).
				if (circuit.isDepositEmpty()) {
					System.out.print("There is no winner \n Te quedaste sin gasofa.");
					return;
				}
				if (circuit.hasWin()) {
					System.out.println("And the winner is: " + currentRocket.getName() + " with a time of "
							+ circuit.getCurrentTime() + "\n" + "Ha ganao pisha! En el segundo: "
							+ circuit.getCurrentTime() + " segundo/s.");
					return;
				}

			}
			circuit.setCurrentTime(circuit.getCurrentTime() + 1);
		}
		System.out.println("There is no winner \n Te quedaste sin tiempo.");
	}
	
	public static Rocket createRocketViperX() {
		List<Propellant> rocketPropellants = new LinkedList<Propellant>();
		rocketPropellants.add(new Propellant(50.0f));
		rocketPropellants.add(new Propellant(20.0f));
		rocketPropellants.add(new Propellant(38.0f));

		Deposit fuelTank = new Deposit(2500.0f);
		Rocket rocket = new Rocket("ViperX", fuelTank);
		rocket.addRocketPropellants(rocketPropellants);
		
		return rocket;
	}

	public static void startRace(Circuit circuit) {
		System.out.println("Starting competition. Circuit length: " + circuit.getCircuitLength() + " Max time: "
				+ circuit.getMaximumTime());
		updateCircuit(circuit);
	}
}
